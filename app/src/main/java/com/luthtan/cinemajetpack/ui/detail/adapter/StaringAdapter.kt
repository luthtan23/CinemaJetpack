package com.luthtan.cinemajetpack.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.ItemCinemaStaringLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.remote.ApiConstant

class StaringAdapter : RecyclerView.Adapter<StaringAdapter.StaringViewHolder>() {

    private val listStaring = ArrayList<CastItem>()

    fun setStaring(staring: List<CastItem>) {
        this.listStaring.clear()
        this.listStaring.addAll(staring)
    }

    inner class StaringViewHolder(private val binding: ItemCinemaStaringLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(staring: CastItem) {
            with(binding) {
                tvItemCinemaStaring.text = staring.name
                Glide.with(itemView.context)
                    .load(ApiConstant.IMAGE_PATH.plus(staring.profilePath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading)
                            .error(R.drawable.ic_baseline_error)
                    )
                    .into(imgPoster)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaringViewHolder {
        val itemCinemaStaringLayoutBinding = ItemCinemaStaringLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StaringViewHolder(itemCinemaStaringLayoutBinding)
    }

    override fun onBindViewHolder(holder: StaringViewHolder, position: Int) {
        holder.bind(listStaring[position])
    }

    override fun getItemCount(): Int = listStaring.size

}