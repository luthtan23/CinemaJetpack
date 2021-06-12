package com.luthtan.cinemajetpack.ui.tvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.ItemCinemaListLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResultsItem
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.ui.tvshow.TvShowFragmentDirections

class TvShowUpcomingAdapter : RecyclerView.Adapter<TvShowUpcomingAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShowResultsItem>()
    private var typeCinema = ""

    fun setTvShow(listtvshow: List<TvShowResultsItem>, typeCinema: String) {
        this.listTvShow.clear()
        this.listTvShow.addAll(listtvshow)
        this.typeCinema = typeCinema
        notifyDataSetChanged()
    }

    inner class TvShowViewHolder(private val binding: ItemCinemaListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowResultsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(ApiConstant.IMAGE_PATH.plus(tvshow.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading)
                            .error(R.drawable.ic_baseline_error))
                    .into(imgPoster)

                imgPoster.setOnClickListener{
                    itemView.findNavController().navigate(
                        TvShowFragmentDirections.actionTvShowFragmentToDetailCinemaFragment2(
                            tvshow.id,
                            typeCinema
                        )
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowListLayoutBinding = ItemCinemaListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvShowListLayoutBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size
}