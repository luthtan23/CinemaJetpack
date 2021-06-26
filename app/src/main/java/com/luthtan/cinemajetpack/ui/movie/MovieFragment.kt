package com.luthtan.cinemajetpack.ui.movie

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
import com.luthtan.cinemajetpack.viewmodel.MovieViewModel
import com.luthtan.cinemajetpack.vo.Resource
import com.luthtan.cinemajetpack.vo.Status
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
        if (!statusNetwork) movieViewModel.getMovieResponse()
        getData()
        setAdapter()
    }

    private fun getData() {
        movieViewModel.moviePopularResponse.observe(viewLifecycleOwner, moviePopularResponse)
        movieViewModel.movieNowPlayingResponse.observe(viewLifecycleOwner, movieNowPlayingResponse)
        movieViewModel.movieTopRatedResponse.observe(viewLifecycleOwner, movieTopRatedResponse)
        movieViewModel.movieUpcomingResponse.observe(viewLifecycleOwner, movieUpcomingResponse)
    }

    private fun setAdapter() {
        val compositePagerTransformer = CompositePageTransformer()
        with(compositePagerTransformer) {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.05f + r + 00.15f
            }
        }
        with(binding.rvMoviePopular) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = moviePopularAdapter
        }
        with(binding.rvMovieUpcoming) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieUpcomingAdapter
        }
        with(binding.rvMovieTopRated) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieTopRatedAdapter
        }
        with(binding.carouselLayoutMovie.viewpagerMovie) {
            adapter = carouselMovieAdapter
            setPageTransformer(compositePagerTransformer)
            registerOnPageChangeCallback(registerViewPagerCallback)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private val moviePopularResponse: Observer<Resource<MovieResponse>> by lazy {
        Observer<Resource<MovieResponse>> { movieResponse ->
            if (movieResponse != null) {
                when (movieResponse.status) {
                    Status.SUCCESS -> {
                        moviePopularAdapter.setMovie(
                            movieResponse.data!!.results,
                            Constant.TYPE_MOVIE
                        )
                        moviePopularAdapter.notifyDataSetChanged()
                        statusNetwork = true
                    }
                    Status.ERROR -> statusNetwork = false
                    Status.LOADING -> {
                    }
                }
            }
            setInitNetworkErrorLayout(statusNetwork)
        }
    }

    private val movieNowPlayingResponse: Observer<Resource<MovieResponse>> by lazy {
        Observer<Resource<MovieResponse>> { movieResponse ->
            if (movieResponse != null) {
                when (movieResponse.status) {
                    Status.SUCCESS -> {
                        carouselMovieAdapter.setCarousel(
                            movieResponse.data!!.results,
                            Constant.TYPE_MOVIE
                        )
                        carouselMovieAdapter.notifyDataSetChanged()
                        statusNetwork = true
                    }
                    Status.ERROR -> statusNetwork = false
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val movieTopRatedResponse: Observer<Resource<MovieResponse>> by lazy {
        Observer<Resource<MovieResponse>> { movieResponse ->
            if (movieResponse != null) {
                when (movieResponse.status) {
                    Status.SUCCESS -> {
                        movieTopRatedAdapter.setMovie(
                            movieResponse.data!!.results,
                            Constant.TYPE_MOVIE
                        )
                        movieTopRatedAdapter.notifyDataSetChanged()
                        statusNetwork = true
                    }
                    Status.ERROR -> statusNetwork = false
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val movieUpcomingResponse: Observer<Resource<MovieResponse>> by lazy {
        Observer<Resource<MovieResponse>> { movieResponse ->
            if (movieResponse != null) {
                when (movieResponse.status) {
                    Status.SUCCESS -> {
                        movieUpcomingAdapter.setMovie(
                            movieResponse.data!!.results,
                            Constant.TYPE_MOVIE
                        )
                        movieUpcomingAdapter.notifyDataSetChanged()
                        statusNetwork = true
                    }
                    Status.ERROR -> statusNetwork = false
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private fun setInitNetworkErrorLayout(status: Boolean) {
        when (status) {
            true -> {
                binding.constraintMovieError.constraintNetworkError.visibility = View.GONE
                binding.constraintMovie.visibility = View.VISIBLE
            }
            false -> {
                binding.constraintMovieError.constraintNetworkError.visibility = View.VISIBLE
                binding.constraintMovie.visibility = View.GONE
            }
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