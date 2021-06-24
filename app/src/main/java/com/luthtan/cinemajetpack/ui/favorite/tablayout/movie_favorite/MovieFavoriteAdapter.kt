package com.luthtan.cinemajetpack.ui.favorite.tablayout.movie_favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luthtan.cinemajetpack.databinding.ItemFavoriteLayoutBinding
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity

class MovieFavoriteAdapter : RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteViewHolder>() {

    private val movieItemDB = ArrayList<DetailEntity>()

    fun setMovieItemDB(detailEntity: List<DetailEntity>) {
        this.movieItemDB.clear()
        this.movieItemDB.addAll(detailEntity)
        notifyDataSetChanged()
    }

    inner class MovieFavoriteViewHolder(private val binding: ItemFavoriteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailEntity: DetailEntity) {
            with(binding) {

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val itemFavoriteLayoutBinding =
            ItemFavoriteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavoriteViewHolder(itemFavoriteLayoutBinding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        holder.bind(movieItemDB[position])
    }

    override fun getItemCount(): Int = movieItemDB.size
}