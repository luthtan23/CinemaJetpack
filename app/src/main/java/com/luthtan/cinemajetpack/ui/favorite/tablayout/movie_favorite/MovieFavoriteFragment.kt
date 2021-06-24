package com.luthtan.cinemajetpack.ui.favorite.tablayout.movie_favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.luthtan.cinemajetpack.databinding.FavoriteMovieLayoutBinding
import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import com.luthtan.cinemajetpack.viewmodel.MovieFavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()

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

        detailViewModel.getAllMovieFavorite().observe(viewLifecycleOwner, { it ->
            if (it != null) {
                Log.e("DB_VALUE", it.toString())
                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "NULL", Toast.LENGTH_LONG).show()
            }
        })
    }

}