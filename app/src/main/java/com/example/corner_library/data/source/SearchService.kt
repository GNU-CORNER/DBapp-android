package com.example.corner_library.data.source

import com.example.corner_library.data.model.SuggestionRequest
import com.example.corner_library.data.model.SuggestionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchService {
    // 자동완성 검색
    @POST("/suggestions/_search/")
    suspend fun searchSuggestion(
        @Body suggestionRequest: SuggestionRequest
    ): Response<SuggestionResponse>
}