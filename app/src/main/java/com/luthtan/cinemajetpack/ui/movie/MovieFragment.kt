package com.luthtan.cinemajetpack.ui.movie

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.MovieFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.ui.movie.adapter.CarouselMovieAdapter
import com.luthtan.cinemajetpack.ui.movie.adapter.MoviePopularAdapter
import com.luthtan.cinemajetpack.ui.movie.adapter.MovieTopRatedAdapter
import com.luthtan.cinemajetpack.ui.movie.adapter.MovieUpcomingAdapter
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.util.Utils
import com.luthtan.cinemajetpack.viewmodel.MovieViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class MovieFragment : Fragment(), View.OnClickListener {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var moviePopularAdapter: MoviePopularAdapter
    private lateinit var carouselMovieAdapter: CarouselMovieAdapter
    private lateinit var movieTopRatedAdapter: MovieTopRatedAdapter
    private lateinit var movieUpcomingAdapter: MovieUpcomingAdapter

    private val handler = Handler()

    private var statusNetwork: Boolean = false

    var _binding: MovieFragmentLayoutBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviePopularAdapter = MoviePopularAdapter()
        movieTopRatedAdapter = MovieTopRatedAdapter()
        movieUpcomingAdapter = MovieUpcomingAdapter()
        carouselMovieAdapter = CarouselMovieAdapter(binding.carouselLayoutMovie.viewpagerMovie)

        binding.constraintMovieError.btnNetworkErrorTryAgain.setOnClickListener(this)

        setInit()
    }

    private fun setInit() {
        if (!statusNetwork) {
            GlobalScope.launch {
                movieViewModel.getMovieResponse()
            }
        }
        getData()
    }

    private fun getData() {
        movieViewModel.moviePopularResponse.observe(viewLifecycleOwner, { movieResponse ->
            if (movieResponse != null) {
                setPopularMovie(movieResponse)
                statusNetwork = true
            }
        })

        movieViewModel.movieNowPlayingResponse.observe(viewLifecycleOwner, { movieResponse ->
            if (movieResponse != null) {
                setViewPager(movieResponse)
            }
        })

        movieViewModel.movieTopRatedResponse.observe(viewLifecycleOwner, { movieResponse ->
            if (movieResponse != null) {
                setTopRatedMovie(movieResponse)
            }
        })

        movieViewModel.movieUpcomingResponse.observe(viewLifecycleOwner, { movieResponse ->
            if (movieResponse != null) {
                setUpcomingMovie(movieResponse)
            }
        })

        movieViewModel.errorResponse.observe(viewLifecycleOwner, { errorResponse ->
            if (errorResponse != null) {
                Utils.snackBarErrorConnection(requireView(), requireContext())
                if (!statusNetwork) {
                    setInitNetworkErrorLayout(true)
                }
            } else setInitNetworkErrorLayout(false)
        })
    }

    private fun setInitNetworkErrorLayout(status: Boolean) {
        when (status) {
            true -> {
                binding.constraintMovieError.constraintNetworkError.visibility = View.VISIBLE
                binding.constraintMovie.visibility = View.GONE
            }
            false -> {
                binding.constraintMovieError.constraintNetworkError.visibility = View.GONE
                binding.constraintMovie.visibility = View.VISIBLE
            }
        }

    }

    private fun setUpcomingMovie(movieResponse: MovieResponse) {
        movieUpcomingAdapter.setMovie(movieResponse.results, Constant.TYPE_MOVIE)
        with(binding.rvMovieUpcoming) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieUpcomingAdapter
        }
    }

    private fun setPopularMovie(response: MovieResponse) {
        moviePopularAdapter.setMovie(response.results, Constant.TYPE_MOVIE)
        with(binding.rvMoviePopular) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = moviePopularAdapter
        }
    }

    private fun setTopRatedMovie(response: MovieResponse) {
        movieTopRatedAdapter.setMovie(response.results, Constant.TYPE_MOVIE)
        with(binding.rvMovieTopRated) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieTopRatedAdapter
        }
    }

    private fun setViewPager(movieResponse: MovieResponse) {
        carouselMovieAdapter.setCarousel(movieResponse.results, Constant.TYPE_MOVIE)
        val compositePagerTransformer = CompositePageTransformer()
        with(compositePagerTransformer) {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.05f + r + 00.15f
            }
        }

        with(binding.carouselLayoutMovie.viewpagerMovie) {
            adapter = carouselMovieAdapter
            setPageTransformer(compositePagerTransformer)
            registerOnPageChangeCallback(registerViewPagerCallback)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

    }

    private val registerViewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            with(handler) {
                removeCallbacks(slideRunnable)
                postDelayed(slideRunnable, 7000)
            }
        }
    }

    private val slideRunnable = Runnable {
        with(binding.carouselLayoutMovie.viewpagerMovie) {
            currentItem += 1
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_network_error_try_again -> setInit()
        }
    }

}