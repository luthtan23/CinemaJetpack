@file:Suppress("Annotator")

package com.luthtan.cinemajetpack.ui.favorite.tablayout.movie_favorite

import android.app.ProgressDialog
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
import com.luthtan.cinemajetpack.databinding.FavoriteMovieLayoutBinding
import com.luthtan.cinemajetpack.listener.DeleteFavoriteListener
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter

    private var _binding: FavoriteMovieLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteMovieLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog.show()

        itemTouchHelper.attachToRecyclerView(binding.rvFavoriteMovieLayout)

        movieFavoriteAdapter = MovieFavoriteAdapter(deleteFavoriteListener)

        detailViewModel.getAllMovieFavorite().observe(viewLifecycleOwner, { movieFavorite ->
            if (movieFavorite != null) {
                progressDialog.dismiss()
                movieFavoriteAdapter.submitList(movieFavorite)
                if (movieFavorite.isNotEmpty()) {
                    setMovieFavoriteEmpty(false)
                } else {
                    setMovieFavoriteEmpty(true)
                }
            }
        })

        with(binding.rvFavoriteMovieLayout) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieFavoriteAdapter
        }
    }

    private val deleteFavoriteListener = object : DeleteFavoriteListener {
        override fun selectedDeleteFavorite(detailEntity: DetailEntity) {
            detailViewModel.deleteMovieFavorite(detailEntity)
        }
    }

    private fun setMovieFavoriteEmpty(status: Boolean) {
        when (status) {
            true -> {
                binding.ivMovieFavoriteNotFound.visibility = View.VISIBLE
                binding.tvMovieFavoriteNotFound.visibility = View.VISIBLE
            }
            false -> {
                binding.ivMovieFavoriteNotFound.visibility = View.GONE
                binding.tvMovieFavoriteNotFound.visibility = View.GONE
            }
        }
    }

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
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
                val courseEntity = movieFavoriteAdapter.getSwipePosition(swipedPosition)
                courseEntity?.let { detailViewModel.deleteMovieFavorite(it) }
                val snackbar = Snackbar.make(
                    view as View,
                    R.string.favorite_movie_deleted_message,
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction(R.string.undo) { v ->
                    courseEntity?.let { detailViewModel.deleteMovieFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

}