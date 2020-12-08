package com.techbulls.testmovies.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kushal.tweetitswet.network.WebServiceUtils.API_KEY
import com.techbulls.testmovies.R
import com.techbulls.testmovies.BR
import com.techbulls.testmovies.TheApp
import com.techbulls.testmovies.adapters.MoviesAdapter
import com.techbulls.testmovies.base.BaseFragment
import com.techbulls.testmovies.databinding.FragmentMoviesBinding
import com.techbulls.testmovies.hideKeyboard
import com.techbulls.testmovies.model.MoviesListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private lateinit var adapterMovie: MoviesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    var fetchLastSearch : Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMoviesBinding = getViewDataBinding()
        viewManager = LinearLayoutManager(activity)
        fragmentMoviesBinding.imgSearch.setOnClickListener {
            getMoviewsList("batman");
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        moviesViewModel.movieName.observe(viewLifecycleOwner, Observer {
            if(it.length>4 && !fetchLastSearch){
                fetchLastSearch=true
                hideKeyboard()
                getMoviewsList(it)
            }else{
                getMoviewsList("batman");
            }
        })
    }

    fun getMoviewsList(searchValue: String) {
        val request = TheApp.apiServiceInterface?.searchMoviesList(searchValue, API_KEY)
        if (request != null) {
            request.enqueue(object : Callback<MoviesListResponse> {
                override fun onResponse(
                    call: Call<MoviesListResponse>,
                    response: Response<MoviesListResponse>
                ) {
                    var mResponseList = response.body()
                    if (mResponseList != null) {
                       var mMoviesList = mResponseList?.movies
                        fetchLastSearch=false
                        if(mMoviesList.isNullOrEmpty()){
                            fragmentMoviesBinding.tvEmptyList.visibility = View.VISIBLE
                            fragmentMoviesBinding.recyclerViewMoviewsList.visibility = View.GONE
                            fragmentMoviesBinding.tvEmptyList.text = activity?.resources?.getString(R.string.enterText)
                        }else{
                            fragmentMoviesBinding.recyclerViewMoviewsList.visibility = View.VISIBLE
                            adapterMovie = mMoviesList?.let { MoviesAdapter(it) }
                            fragmentMoviesBinding.tvEmptyList.visibility = View.GONE
                            fragmentMoviesBinding.recyclerViewMoviewsList.apply {
                                setHasFixedSize(true)
                                layoutManager = viewManager
                                adapter = adapterMovie

                            }
                        }

                    }
                }

                override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                    fetchLastSearch=false
                    fragmentMoviesBinding.tvEmptyList.visibility = View.VISIBLE
                }

            })
        }

    }


    override fun getLayoutId() = R.layout.fragment_movies
    override fun getBindingVariable() = BR.moviewData
    override fun getViewModel() = moviesViewModel
}