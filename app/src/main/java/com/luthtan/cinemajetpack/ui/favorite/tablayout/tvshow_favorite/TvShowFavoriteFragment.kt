package com.luthtan.cinemajetpack.ui.favorite.tablayout.tvshow_favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

        tvShowFavoriteAdapter = TvShowFavoriteAdapter()

        detailViewModel.getAllTvShowFavorite().observe(viewLifecycleOwner, { tvFavorite ->
            if (tvFavorite != null) {
                tvShowFavoriteAdapter.setTvShowItemDB(tvFavorite, deleteFavoriteListener)
                tvShowFavoriteAdapter.notifyDataSetChanged()
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
}