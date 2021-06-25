package com.luthtan.cinemajetpack.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.ItemCinemaRecommendationBinding
import com.luthtan.cinemajetpack.model.bean.local.RecommendationItemsEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationItems
import com.luthtan.cinemajetpack.model.remote.ApiConstant

class RecommendationAdapter :
    RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    private val listRecommendation = ArrayList<RecommendationItemsEntity>()
    private var typeCinema = ""

    fun setRecommendation(listRecommendation: List<RecommendationItemsEntity>, typeCinema: String) {
        this.listRecommendation.clear()
        this.listRecommendation.addAll(listRecommendation)
        this.typeCinema = typeCinema
        notifyDataSetChanged()
    }

    inner class RecommendationViewHolder(private val binding: ItemCinemaRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendationItems: RecommendationItemsEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(ApiConstant.IMAGE_PATH.plus(recommendationItems.backdropPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading_carousel)
                            .error(R.drawable.ic_baseline_error)
                    )
                    .into(imgPoster)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val itemCinemaRecommendationBinding = ItemCinemaRecommendationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecommendationViewHolder(itemCinemaRecommendationBinding)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(listRecommendation[position])
    }

    override fun getItemCount(): Int = listRecommendation.size
}