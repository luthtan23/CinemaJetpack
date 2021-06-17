package com.luthtan.cinemajetpack.ui.tvshow

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
import com.luthtan.cinemajetpack.databinding.TvshowFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.ui.tvshow.adapter.CarouselTvShowAdapter
import com.luthtan.cinemajetpack.ui.tvshow.adapter.TvShowPopularAdapter
import com.luthtan.cinemajetpack.ui.tvshow.adapter.TvShowTopRatedAdapter
import com.luthtan.cinemajetpack.ui.tvshow.adapter.TvShowUpcomingAdapter
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.util.Utils
import com.luthtan.cinemajetpack.viewmodel.TvShowViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

@Suppress("DEPRECATION")
class TvShowFragment : Fragment(), View.OnClickListener {

    private val tvShowViewModel: TvShowViewModel by viewModel()
    private lateinit var tvShowPopularAdapter: TvShowPopularAdapter
    private lateinit var tvShowTopRatedAdapter: TvShowTopRatedAdapter
    private lateinit var tvShowUpcomingAdapter: TvShowUpcomingAdapter
    private lateinit var carouselTvShowAdapter: CarouselTvShowAdapter

    private val handler = Handler()

    private var statusNetwork: Boolean = false

    private var _binding: TvshowFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvshowFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowPopularAdapter = TvShowPopularAdapter()
        tvShowTopRatedAdapter = TvShowTopRatedAdapter()
        tvShowUpcomingAdapter = TvShowUpcomingAdapter()
        carouselTvShowAdapter = CarouselTvShowAdapter(binding.carouselLayoutTvshow.viewpagerMovie)

        setInit()

        binding.constraintTvShowError.btnNetworkErrorTryAgain.setOnClickListener(this)
    }

    private fun setInit() {
        if (!statusNetwork) {
            GlobalScope.launch {
                tvShowViewModel.getTvShowResponse()
            }
        }
        getData()
    }

    private fun getData() {
        tvShowViewModel.tvShowPopularResponse.observe(viewLifecycleOwner, { tvshowResponse ->
            if (tvshowResponse != null) {
                setPopularTvShow(tvshowResponse)
                statusNetwork = true
            }
        })

        tvShowViewModel.tvShowNowPlayingResponse.observe(viewLifecycleOwner, { tvshowResponse ->
            if (tvshowResponse != null) {
                setViewPager(tvshowResponse)
            }
        })

        tvShowViewModel.tvShowTopRatedResponse.observe(viewLifecycleOwner, { tvshowResponse ->
            if (tvshowResponse != null) {
                setTopRatedTvShow(tvshowResponse)
            }
        })

        tvShowViewModel.tvShowUpcomingResponse.observe(viewLifecycleOwner, { tvshowResponse ->
            if (tvshowResponse != null) {
                setUpcomingTvShow(tvshowResponse)
            }
        })

        tvShowViewModel.errorResponse.observe(viewLifecycleOwner, { errorResponse ->
            if (errorResponse != null) {
                Utils.snackBarErrorConnection(requireView(), requireContext())
                if (!statusNetwork) setInitNetworkErrorLayout(true)
            } else setInitNetworkErrorLayout(false)
        })
    }

    private fun setInitNetworkErrorLayout(status: Boolean) {
        when (status) {
            true -> {
                binding.constraintTvShowError.constraintNetworkError.visibility = View.VISIBLE
                binding.constraintTvShow.visibility = View.GONE
            }
            false -> {
                binding.constraintTvShowError.constraintNetworkError.visibility = View.GONE
                binding.constraintTvShow.visibility = View.VISIBLE
            }
        }

    }

    private fun setViewPager(tvshowResponse: TvShowResponse) {
        carouselTvShowAdapter.setCarousel(tvshowResponse.results!!, Constant.TYPE_TV_SHOW)
        val compositePagerTransformer = CompositePageTransformer()
        with(compositePagerTransformer) {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.05f + r + 00.15f
            }
        }

        with(binding.carouselLayoutTvshow.viewpagerMovie) {
            adapter = carouselTvShowAdapter
            setPageTransformer(compositePagerTransformer)
            registerOnPageChangeCallback(registerViewPagerCallback)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun setUpcomingTvShow(tvshowResponse: TvShowResponse) {
        tvShowUpcomingAdapter.setTvShow(tvshowResponse.results!!, Constant.TYPE_TV_SHOW)
        with(binding.rvTvshowUpcoming) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowUpcomingAdapter
        }
    }

    private fun setPopularTvShow(tvshowResponse: TvShowResponse) {
        tvShowPopularAdapter.setTvShow(tvshowResponse.results!!, Constant.TYPE_TV_SHOW)
        with(binding.rvTvshowPopular) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowPopularAdapter
        }
    }

    private fun setTopRatedTvShow(tvshowResponse: TvShowResponse) {
        tvShowTopRatedAdapter.setTvShow(tvshowResponse.results!!, Constant.TYPE_TV_SHOW)
        with(binding.rvTvshowTopRated) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowTopRatedAdapter
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
        with(binding.carouselLayoutTvshow.viewpagerMovie) {
            currentItem += 1
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_network_error_try_again -> setInit()
        }
    }


}