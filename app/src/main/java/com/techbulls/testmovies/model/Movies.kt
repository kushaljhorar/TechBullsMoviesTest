package com.techbulls.testmovies.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("Title")
    val Title: String,
    @SerializedName("Year")
    val Year: String,
    @SerializedName("Poster")
    val Poster: String,
)