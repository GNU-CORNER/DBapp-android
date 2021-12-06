package com.example.corner_library.data.model

import com.google.gson.annotations.SerializedName

data class SuggestionResponse(
    @SerializedName("took")
    val took: Int,

    @SerializedName("timed_out")
    val timedOut: Boolean,

    @SerializedName("_shards")
    val shards: Shards,

    @SerializedName("hits")
    val hits: Hits
)

data class Shards(
    @SerializedName("total")
    val total: Int,

    @SerializedName("successful")
    val successful: Int,

    @SerializedName("skipped")
    val skipped: Int,

    @SerializedName("failed")
    val failed: Int
)

data class Hits(
    @SerializedName("total")
    val total: Total,

    @SerializedName("max_score")
    val maxScore: Double,

    @SerializedName("hits")
    val hits: List<Hit>?
)

data class Total(
    @SerializedName("value")
    val value: Int,

    @SerializedName("relation")
    val relation: String
)

data class Hit(
    @SerializedName("_index")
    val index: String,

    @SerializedName("_type")
    val type: String,

    @SerializedName("_id")
    val id: String,

    @SerializedName("_score")
    val score: Double,

    @SerializedName("_source")
    val source: Suggestion
)