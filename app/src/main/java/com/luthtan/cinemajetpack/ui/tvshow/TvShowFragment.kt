package com.luthtan.cinemajetpack.ui.tvshow

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
import com.luthtan.cinemajetpack.databinding.TvshowFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.ui.tvshow.adapter.CarouselTvShowAdapter
import com.luthtan.cinemajetpack.ui.tvshow.adapter.TvShowPopularAdapter
import com.luthtan.cinemajetpack.ui.tvshow.adapter.TvShowTopRatedAdapter
import com.luthtan.cinemajetpack.ui.tvshow.adapter.TvShowUpcomingAdapter
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.viewmodel.TvShowViewModel
import com.luthtan.cinemajetpack.vo.Resource
import com.luthtan.cinemajetpack.vo.Status
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
            tvShowViewModel.getTvShowResponse()
        }
        getData()
        setAdapter()
    }

    private fun getData() {
        tvShowViewModel.tvShowPopularResponse.observe(viewLifecycleOwner, tvShowPopularResponse)
        tvShowViewModel.tvShowNowPlayingResponse.observe(
            viewLifecycleOwner,
            tvShowNowPlayingResponse
        )
        tvShowViewModel.tvShowTopRatedResponse.observe(viewLifecycleOwner, tvShowTopRatedResponse)
        tvShowViewModel.tvShowUpcomingResponse.observe(viewLifecycleOwner, tvShowUpcomingResponse)
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
        with(binding.rvTvshowPopular) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowPopularAdapter
        }
        with(binding.carouselLayoutTvshow.viewpagerMovie) {
            adapter = carouselTvShowAdapter
            setPageTransformer(compositePagerTransformer)
            registerOnPageChangeCallback(registerViewPagerCallback)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        with(binding.rvTvshowUpcoming) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowUpcomingAdapter
        }
        with(binding.rvTvshowTopRated) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowTopRatedAdapter
        }
        setInitNetworkErrorLayout(statusNetwork)
    }

    private val tvShowPopularResponse: Observer<Resource<TvShowResponse>> by lazy {
        Observer<Resource<TvShowResponse>> { tvShowResponse ->
            if (tvShowResponse != null) {
                when (tvShowResponse.status) {
                    Status.SUCCESS -> {
                        tvShowPopularAdapter.setTvShow(
                            tvShowResponse.data!!.results!!,
                            Constant.TYPE_TV_SHOW
                        )
                        tvShowPopularAdapter.notifyDataSetChanged()
                        statusNetwork = false
                    }
                    Status.ERROR -> {
                        statusNetwork = true
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val tvShowNowPlayingResponse: Observer<Resource<TvShowResponse>> by lazy {
        Observer<Resource<TvShowResponse>> { tvShowResponse ->
            if (tvShowResponse != null) {
                when (tvShowResponse.status) {
                    Status.SUCCESS -> {
                        carouselTvShowAdapter.setCarousel(
                            tvShowResponse.data!!.results!!,
                            Constant.TYPE_TV_SHOW
                        )
                        carouselTvShowAdapter.notifyDataSetChanged()
                        statusNetwork = false
                    }
                    Status.ERROR -> {
                        statusNetwork = true
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val tvShowUpcomingResponse: Observer<Resource<TvShowResponse>> by lazy {
        Observer<Resource<TvShowResponse>> { tvShowResponse ->
            if (tvShowResponse != null) {
                when (tvShowResponse.status) {
                    Status.SUCCESS -> {
                        tvShowUpcomingAdapter.setTvShow(
                            tvShowResponse.data!!.results!!,
                            Constant.TYPE_TV_SHOW
                        )
                        tvShowUpcomingAdapter.notifyDataSetChanged()
                        statusNetwork = false
                    }
                    Status.ERROR -> {
                        statusNetwork = true
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val tvShowTopRatedResponse: Observer<Resource<TvShowResponse>> by lazy {
        Observer<Resource<TvShowResponse>> { tvShowResponse ->
            if (tvShowResponse != null) {
                when (tvShowResponse.status) {
                    Status.SUCCESS -> {
                        tvShowTopRatedAdapter.setTvShow(
                            tvShowResponse.data!!.results!!,
                            Constant.TYPE_TV_SHOW
                        )
                        tvShowTopRatedAdapter.notifyDataSetChanged()
                        statusNetwork = false
                    }
                    Status.ERROR -> {
                        statusNetwork = true
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
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