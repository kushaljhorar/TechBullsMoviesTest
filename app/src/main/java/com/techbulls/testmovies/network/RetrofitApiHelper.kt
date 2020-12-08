package com.kushal.tweetitswet.network

import com.google.gson.GsonBuilder
import com.techbulls.testmovies.TheApp.Companion.retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import java.util.concurrent.TimeUnit

object RetrofitApiHelper {
    fun getRequestQueue(): Retrofit? {
        if (retrofit == null) {
            val responseParserFactory = GsonBuilder().create()
            retrofit = Retrofit.Builder()
                .baseUrl(WebServiceUtils.BASE_URL)
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(responseParserFactory))
                .build()
        }
        return retrofit
    }

    /**
     * @method use for provide ok Http client
     */
    private fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            //.addInterceptor(SigningInterceptor(getOAuthConsumer()))
            .build()
    }



    /**
     * @method use for provide Http Logging Interceptor
     */
    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}


