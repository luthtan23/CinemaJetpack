package com.luthtan.cinemajetpack.ui.favorite.tablayout.movie_favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.ItemFavoriteLayoutBinding
import com.luthtan.cinemajetpack.listener.CustomOnItemClickListener
import com.luthtan.cinemajetpack.listener.DeleteFavoriteListener
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.ui.favorite.FavoriteFragmentDirections
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.util.Utils

class MovieFavoriteAdapter(private val deleteFavoriteListener: DeleteFavoriteListener) :
    PagedListAdapter<DetailEntity, MovieFavoriteAdapter.MovieFavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailEntity>() {
            override fun areItemsTheSame(oldItem: DetailEntity, newItem: DetailEntity): Boolean {
                return oldItem.detailId == newItem.detailId
            }
            override fun areContentsTheSame(oldItem: DetailEntity, newItem: DetailEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipePosition(swipePosition: Int): DetailEntity? = getItem(swipePosition)

    inner class MovieFavoriteViewHolder(private val binding: ItemFavoriteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailEntity: DetailEntity) {
            with(binding) {
                val userScore = detailEntity.voteAverage * 10
                tvItemFavoriteTagline.text = detailEntity.tagline
                tvItemFavoriteGenre.text = Utils.insertStringGenre(detailEntity.genres)
                progressBarItemFavoriteUserScore.progress = userScore.toInt()
                tvItemFavoriteUserScore.text = userScore.toString()
                tvItemFavoriteTitle.text = detailEntity.originalTitle
                tvItemFavoriteReleasedDate.text = detailEntity.releaseDate
                tvItemFavoriteDuration.text = detailEntity.runtime.toString().plus("m")
                ibItemFavoriteShare.setOnClickListener {
                    deleteFavoriteListener.selectedDeleteFavorite(detailEntity)
                }
                Glide.with(itemView.context)
                    .load(ApiConstant.IMAGE_PATH.plus(detailEntity.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading)
                            .error(R.drawable.ic_baseline_error)
                    )
                    .into(ivItemFavorite)

                itemView.setOnClickListener(CustomOnItemClickListener(object :
                    CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View) {
                        itemView.findNavController().navigate(
                            FavoriteFragmentDirections.actionFavoriteFragmentToDetailCinemaFragment(
                                detailEntity.detailId,
                                Constant.TYPE_MOVIE
                            )
                        )
                    }
                }))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val itemFavoriteLayoutBinding =
            ItemFavoriteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavoriteViewHolder(itemFavoriteLayoutBinding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val detailEntity = getItem(position)
        if (detailEntity != null) {
            holder.bind(detailEntity)
        }
    }

}