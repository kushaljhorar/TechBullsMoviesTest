package com.kushal.tweetitswet.network


import com.kushal.tweetitswet.network.WebServiceUtils.BASE_URL
import com.techbulls.testmovies.model.Movies
import com.techbulls.testmovies.model.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET(BASE_URL)
    fun moviesDetail(
        @Query("i") i: String?,
        @Query("apikey") apikey: String?
    ): Call<Movies>

    @GET(BASE_URL)
    fun searchMoviesList(
        @Query("s") i: String?,
        @Query("apikey") apikey: String?
    ): Call<MoviesListResponse>


}