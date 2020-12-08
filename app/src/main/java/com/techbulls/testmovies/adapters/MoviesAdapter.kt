package com.techbulls.testmovies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.techbulls.testmovies.R
import com.techbulls.testmovies.model.Movies


class MoviesAdapter(val moviestList: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_movie_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.bindItems(moviestList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return moviestList.size
    }

   /* fun sortBySearch(seachCount: Boolean) {
        if (!moviestList.isEmpty()) {
            Collections.sort(moviestList, SortHelper(seachCount))
            this.notifyDataSetChanged()
        }
    }*/

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(moviewIndex: Movies) {
            val textName = itemView.findViewById(R.id.tvMoviewName) as TextView
            val textDes = itemView.findViewById(R.id.tvMovieYear) as TextView
            val img = itemView.findViewById(R.id.imgMovie) as ImageView
            textName.text = moviewIndex.Title
            textDes.text = moviewIndex.Year

            img.load(moviewIndex.Poster) {
                crossfade(true)
                placeholder(R.drawable.noimg)
                transformations(CircleCropTransformation())
            }
        }
    }
}