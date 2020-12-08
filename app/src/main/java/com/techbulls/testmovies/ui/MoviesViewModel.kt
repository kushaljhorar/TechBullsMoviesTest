package com.techbulls.testmovies.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.techbulls.testmovies.base.BaseViewModel

class MoviesViewModel(application: Application) : BaseViewModel(application) {
    val movieName = MutableLiveData<String>()
}