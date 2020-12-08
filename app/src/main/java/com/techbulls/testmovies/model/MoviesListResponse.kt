package com.techbulls.testmovies.model

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("Search")
    val movies: List<Movies>
)