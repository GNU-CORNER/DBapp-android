package com.example.corner_library.repository

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.example.corner_library.data.model.Bool
import com.example.corner_library.data.model.Match
import com.example.corner_library.data.model.Should
import com.example.corner_library.data.model.SuggestionRequest
import com.example.corner_library.data.model.MatchField
import com.example.corner_library.data.model.MatchValue
import com.example.corner_library.data.model.Wildcard
import com.example.corner_library.data.model.WildcardField
import com.example.corner_library.data.model.WildcardValue
import com.example.corner_library.data.source.RemoteDataSource
import java.io.IOException

object SearchRepository {
    private val service = RemoteDataSource.searchService

    // 자동완성 검색
    suspend fun searchSuggestion(query: String): List<SpannableString> {
        var suggestions: List<SpannableString> = listOf()    // 검색 결과 없을 경우 빈 리스트

        if (query.isNotEmpty()) {
            try {
                val analyzer = when (query.length) {    // 검색어 길이에 따른 분석기 설정
                    1 -> "unigram"
                    2 -> "bigram"
                    else -> "trigram"
                }
                val response = service.searchSuggestion(
                    SuggestionRequest(
                        Bool(
                            Should(
                                listOf(
                                    Match(MatchField(MatchValue(analyzer, query))),
                                    Wildcard(WildcardField(WildcardValue("$query*")))
                                )
                            )
                        )
                    )
                )

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        body.hits.hits?.map { hit -> hit.source }?.let {
                            suggestions = it.map { suggestion ->
                                val spannableString = SpannableString(suggestion.name)
                                val start = suggestion.name.indexOf(query, ignoreCase = true)
                                val end = start + query.length

                                if (start >= 0) {   // 검색어와 일치하는 부분 색 변경
                                    spannableString.setSpan(
                                        ForegroundColorSpan(Color.parseColor("#FF6200EE")),
                                        start,
                                        end,
                                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                    )
                                }

                                spannableString
                            }
                        }
                    }
                } else {
                    Log.d("SearchRepository", "result : ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("SearchRepository", e.message.toString())
                e.printStackTrace()
            }
        }

        return suggestions
    }
}