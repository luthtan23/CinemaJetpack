package com.luthtan.cinemajetpack.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.ItemCinemaListLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResultsItem
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.ui.movie.MovieFragmentDirections

class MovieTopRatedAdapter : RecyclerView.Adapter<MovieTopRatedAdapter.MovieViewHolder>() {

    private val listMovie = ArrayList<MovieResultsItem>()
    private var typeCinema = ""

    fun setMovie(listMovie: List<MovieResultsItem>, typeCinema: String) {
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
        this.typeCinema = typeCinema
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemCinemaListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResultsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(ApiConstant.IMAGE_PATH.plus(movie.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading)
                            .error(R.drawable.ic_baseline_error))
                    .into(imgPoster)

                imgPoster.setOnClickListener{
                    itemView.findNavController().navigate(
                        MovieFragmentDirections.actionMovieFragmentToDetailCinemaFragment(
                            movie.id,
                            typeCinema
                        )
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieListLayoutBinding = ItemCinemaListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieListLayoutBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}