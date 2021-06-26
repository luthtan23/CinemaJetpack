package com.luthtan.cinemajetpack.ui.favorite.tablayout.tvshow_favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
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

class TvShowFavoriteAdapter :
    RecyclerView.Adapter<TvShowFavoriteAdapter.TvShowFavoriteViewHolder>() {

    private val tvShowItemDB = ArrayList<DetailEntity>()

    private lateinit var deleteFavoriteListener: DeleteFavoriteListener

    fun setTvShowItemDB(
        detailEntity: List<DetailEntity>,
        deleteFavoriteListener: DeleteFavoriteListener
    ) {
        this.tvShowItemDB.clear()
        this.tvShowItemDB.addAll(detailEntity)
        this.deleteFavoriteListener = deleteFavoriteListener
    }

    inner class TvShowFavoriteViewHolder(private val binding: ItemFavoriteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailEntity: DetailEntity) {
            with(binding) {
                val userScore = detailEntity.voteAverage * 10
                tvItemFavoriteTagline.text = detailEntity.tagline
                tvItemFavoriteTitle.text = detailEntity.name
                tvItemFavoriteReleasedDate.text = detailEntity.firstAirDate
                tvItemFavoriteGenre.text = Utils.insertStringGenre(detailEntity.genres)
                progressBarItemFavoriteUserScore.progress = userScore.toInt()
                tvItemFavoriteUserScore.text = userScore.toString()
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
                            FavoriteFragmentDirections.actionFavoriteFragmentToDetailCinemaFragment2(
                                detailEntity.detailId,
                                Constant.TYPE_TV_SHOW
                            )
                        )
                    }
                }))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteViewHolder {
        val itemFavoriteLayoutBinding =
            ItemFavoriteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowFavoriteViewHolder(itemFavoriteLayoutBinding)
    }

    override fun onBindViewHolder(holder: TvShowFavoriteViewHolder, position: Int) {
        holder.bind(tvShowItemDB[position])
    }

    override fun getItemCount(): Int = tvShowItemDB.size
}