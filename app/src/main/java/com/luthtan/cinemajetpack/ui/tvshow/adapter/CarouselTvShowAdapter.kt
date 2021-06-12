package com.luthtan.cinemajetpack.ui.tvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.ItemCarouselLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResultsItem
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.ui.tvshow.TvShowFragmentDirections

class CarouselTvShowAdapter(private val viewPager2: ViewPager2) : RecyclerView.Adapter<CarouselTvShowAdapter.CarouselViewHolder>() {

    private val listCarousel = ArrayList<TvShowResultsItem>()
    private var typeCinema = ""

    fun setCarousel(listCarousel: List<TvShowResultsItem>, typeCinema: String) {
        this.listCarousel.clear()
        for (i in 0..6) {
            this.listCarousel.add(listCarousel[i])
        }
        this.typeCinema = typeCinema
        notifyDataSetChanged()
    }

    inner class CarouselViewHolder(private val binding: ItemCarouselLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(TvShowResultsItem: TvShowResultsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(ApiConstant.IMAGE_PATH.plus(TvShowResultsItem.backdropPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading_carousel)
                            .error(R.drawable.ic_baseline_error))
                    .into(imgCarouselItem)

                imgCarouselItem.setOnClickListener{
                    itemView.findNavController().navigate(
                        TvShowFragmentDirections.actionTvShowFragmentToDetailCinemaFragment2(
                            TvShowResultsItem.id,
                            typeCinema
                        )
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemCarouselLayoutBinding = ItemCarouselLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(itemCarouselLayoutBinding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(listCarousel[position])
        if (position == itemCount - 2) {
            viewPager2.post(slideRunnable)
        }
    }

    override fun getItemCount(): Int = listCarousel.size

    private val slideRunnable = Runnable {
        listCarousel.addAll(listCarousel)
        notifyDataSetChanged()
    }
}