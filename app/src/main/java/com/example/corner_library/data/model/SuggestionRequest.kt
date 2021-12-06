package com.example.corner_library.data.model

import com.google.gson.annotations.SerializedName

data class SuggestionRequest(
    @SerializedName("query")
    val query: Bool
)

data class Bool(
    @SerializedName("bool")
    val should: Should
)

data class Should(
    @SerializedName("should")
    val queries: List<Any>
)

data class Match(
    @SerializedName("match")
    val field: MatchField
)

data class MatchField(
    @SerializedName("suggestion")
    val value: MatchValue
)

data class MatchValue(
    @SerializedName("analyzer")
    val analyzer: String,

    @SerializedName("query")
    val query: String
)

data class Wildcard(
    @SerializedName("wildcard")
    val field: WildcardField
)

data class WildcardField(
    @SerializedName("suggestion.keyword")
    val value: WildcardValue
)

data class WildcardValue(
    @SerializedName("value")
    val value: String
)
