package com.luthtan.cinemajetpack.ui.favorite.tablayout.movie_favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luthtan.cinemajetpack.databinding.FavoriteMovieLayoutBinding

class MovieFavoriteFragment : Fragment() {

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

}