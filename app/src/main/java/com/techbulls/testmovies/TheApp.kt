package com.techbulls.testmovies

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.kushal.tweetitswet.network.ApiServiceInterface
import com.kushal.tweetitswet.network.RetrofitApiHelper
import retrofit2.Retrofit


class TheApp : MultiDexApplication() {
    companion object {

        var apiServiceInterface: ApiServiceInterface? = null
        var singleton: TheApp? = null
        var retrofit: Retrofit? = null
        @JvmStatic
        fun getInstance(): TheApp? {
            return singleton
        }
    }

    @SuppressLint("NewApi")
    override fun onCreate() {
        super.onCreate()

        singleton = this
        MultiDex.install(singleton)
        Log.d("App", "onCreate")
        apiServiceInterface = RetrofitApiHelper.getRequestQueue()?.create(ApiServiceInterface::class.java)
    }


}