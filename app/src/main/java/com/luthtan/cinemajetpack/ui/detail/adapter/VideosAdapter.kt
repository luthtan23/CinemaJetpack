package com.luthtan.cinemajetpack.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luthtan.cinemajetpack.databinding.ItemCinemaVideosBinding
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerItems
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.util.Utils

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    private val listVideos = ArrayList<TrailerItems>()
    private lateinit var activity: MainActivity

    fun setVideos(activity: MainActivity, videos: List<TrailerItems>) {
        this.listVideos.clear()
        this.listVideos.addAll(videos)
        this.activity = activity
        notifyDataSetChanged()
    }

    inner class VideosViewHolder(private val binding: ItemCinemaVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videos: TrailerItems) {
            with(binding) {
                btnItemDetailContentTrailer.text = videos.name
                btnItemDetailContentTrailer.setOnClickListener {
                    Utils.trailerCinema(activity, videos.key)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val itemCinemaVideosBinding =
            ItemCinemaVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideosViewHolder(itemCinemaVideosBinding)
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bind(listVideos[position])
    }

    override fun getItemCount(): Int = listVideos.size

}