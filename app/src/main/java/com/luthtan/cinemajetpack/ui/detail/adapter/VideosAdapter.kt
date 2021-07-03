package com.luthtan.cinemajetpack.ui.detail.adapter

import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.ItemCinemaVideosBinding
import com.luthtan.cinemajetpack.model.bean.local.TrailerItemsEntity
import com.luthtan.cinemajetpack.ui.MainActivity

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    private val listVideos = ArrayList<TrailerItemsEntity>()
    private val expandableArray = ArrayList<Boolean>()
    private lateinit var activity: MainActivity

    fun setVideos(
        activity: MainActivity,
        videos: List<TrailerItemsEntity>,
        expandableArray: List<Boolean>
    ) {
        this.listVideos.clear()
        this.listVideos.addAll(videos)
        this.expandableArray.clear()
        this.expandableArray.addAll(expandableArray)
        this.activity = activity
        notifyDataSetChanged()
    }

    inner class VideosViewHolder(private val binding: ItemCinemaVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videos: TrailerItemsEntity, position: Int) {
            with(binding) {
                btnItemDetailContentTrailer.text = videos.name
                btnItemDetailContentTrailer.setOnClickListener {
                    expandableArray[position] = !expandableArray[position]
                    val transition = Slide(Gravity.BOTTOM)
                    transition.duration = 600
                    transition.addTarget(R.id.youtube_view_watch_trailer)
                    TransitionManager.beginDelayedTransition(binding.root, transition)
                    youtubeViewWatchTrailer.visibility = if (expandableArray[position]) View.VISIBLE else View.GONE
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
        holder.bind(listVideos[position], position)
    }

    override fun getItemCount(): Int = listVideos.size

}