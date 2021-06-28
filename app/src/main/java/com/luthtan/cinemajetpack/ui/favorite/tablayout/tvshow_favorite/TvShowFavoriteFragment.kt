package com.luthtan.cinemajetpack.ui.favorite.tablayout.tvshow_favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.FavoriteTvshowLayoutBinding
import com.luthtan.cinemajetpack.listener.DeleteFavoriteListener
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    private var _binding: FavoriteTvshowLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvShowFavoriteAdapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteTvshowLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvFavoriteTvShowLayout)

        tvShowFavoriteAdapter = TvShowFavoriteAdapter(deleteFavoriteListener)

        detailViewModel.getAllTvShowFavorite().observe(viewLifecycleOwner, { tvFavorite ->
            if (tvFavorite != null) {
                tvShowFavoriteAdapter.submitList(tvFavorite)
                if (tvFavorite.isNotEmpty()) {
                    setTvShowFavoriteEmpty(false)
                } else {
                    setTvShowFavoriteEmpty(true)
                }
            }
        })

        with(binding.rvFavoriteTvShowLayout) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowFavoriteAdapter
        }
    }

    private val deleteFavoriteListener = object : DeleteFavoriteListener {
        override fun selectedDeleteFavorite(detailEntity: DetailEntity) {
            detailViewModel.deleteTvShowFavorite(detailEntity)
        }
    }

    private fun setTvShowFavoriteEmpty(status: Boolean) {
        when (status) {
            true -> {
                binding.ivTvShowFavoriteNotFound.visibility = View.VISIBLE
                binding.tvTvShowFavoriteNotFound.visibility = View.VISIBLE
            }
            false -> {
                binding.ivTvShowFavoriteNotFound.visibility = View.GONE
                binding.tvTvShowFavoriteNotFound.visibility = View.GONE
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = tvShowFavoriteAdapter.getSwipePosition(swipedPosition)
                courseEntity?.let { detailViewModel.deleteTvShowFavorite(it) }
                val snackbar = Snackbar.make(
                    view as View,
                    R.string.favorite_movie_deleted_message,
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction(R.string.undo) { v ->
                    courseEntity?.let { detailViewModel.deleteTvShowFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}