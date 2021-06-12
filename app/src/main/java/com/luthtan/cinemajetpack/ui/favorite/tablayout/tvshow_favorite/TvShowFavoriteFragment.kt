package com.luthtan.cinemajetpack.ui.favorite.tablayout.tvshow_favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luthtan.cinemajetpack.databinding.FavoriteTvshowLayoutBinding

class TvShowFavoriteFragment : Fragment() {

    private var _binding: FavoriteTvshowLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteTvshowLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}