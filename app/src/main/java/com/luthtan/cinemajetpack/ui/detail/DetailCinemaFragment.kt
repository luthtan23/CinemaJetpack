package com.luthtan.cinemajetpack.ui.detail

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.DetailCinemaFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.ui.detail.adapter.RecommendationAdapter
import com.luthtan.cinemajetpack.ui.detail.adapter.StaringAdapter
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.util.Utils
import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import com.luthtan.cinemajetpack.vo.Resource
import com.luthtan.cinemajetpack.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCinemaFragment : Fragment(), View.OnClickListener {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var staringAdapter: StaringAdapter
    private lateinit var recommendationAdapter: RecommendationAdapter
    val args: DetailCinemaFragmentArgs by navArgs()

    private var _binding: DetailCinemaFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private var extraId: Int = 0
    private var extraType = ""
    private var title = "title"

    private var statusNetwork: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailCinemaFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            extraId = args.id
            extraType = args.typeCinema
        } else {
            extraId = 19913
            extraType = "movie"
        }

        progressDialog.show()

        staringAdapter = StaringAdapter()
        recommendationAdapter = RecommendationAdapter()

        setInit()

        binding.btnDetailContentTrailer.setOnClickListener(this)
        binding.constraintDetailError.btnNetworkErrorTryAgain.setOnClickListener(this)
    }

    private fun setInit() {
        if (extraType == Constant.TYPE_MOVIE) {
            detailViewModel.getDetailMovie(extraId)
        } else {
            detailViewModel.getDetailTvShow(extraId)
        }

        getData()
        setAdapter()
    }

    private fun getData() {
        backPressedFragment()
        detailViewModel.detailResponse.observe(viewLifecycleOwner, detailResponse)
        detailViewModel.creditResponse.observe(viewLifecycleOwner, creditResponse)
        detailViewModel.recommendationResponse.observe(viewLifecycleOwner, recommendationResponse)
    }

    private fun setAdapter() {
        setInitNetworkErrorLayout(statusNetwork)
        with(binding.rvDetailContentStaring) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = staringAdapter
        }
        with(binding.rvDetailContentRecommendation) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = recommendationAdapter
        }
    }

    private val detailResponse: Observer<Resource<DetailResponse>> by lazy {
        Observer<Resource<DetailResponse>> { detailResponse ->
            if (detailResponse != null) {
                when (detailResponse.status) {
                    Status.SUCCESS -> {
                        setDetailAttr(detailResponse.data!!)
                        if (extraType == Constant.TYPE_MOVIE) {
                            setDetailAttrMovie(detailResponse.data)
                        } else {
                            setDetailAttrTvShow(detailResponse.data)
                        }
                        statusNetwork = false
                    }
                    Status.ERROR -> statusNetwork = true
                    Status.LOADING -> {}
                }
            }
        }
    }

    private val creditResponse: Observer<Resource<CreditResponse>> by lazy {
        Observer<Resource<CreditResponse>> { creditResponse ->
            if (creditResponse != null) {
                when (creditResponse.status) {
                    Status.SUCCESS -> {
                        staringAdapter.setStaring(creditResponse.data!!.cast)
                        staringAdapter.notifyDataSetChanged()
                        statusNetwork = false
                    }
                    Status.ERROR -> statusNetwork = true
                    Status.LOADING -> {}
                }
            }
        }
    }

    private val recommendationResponse: Observer<Resource<RecommendationResponse>> by lazy {
        Observer<Resource<RecommendationResponse>> { recommendationResponse ->
            if (recommendationResponse != null) {
                when (recommendationResponse.status) {
                    Status.SUCCESS -> {
                        recommendationAdapter.setRecommendation(
                            recommendationResponse.data!!.results!!,
                            Constant.TYPE_MOVIE
                        )
                        staringAdapter.notifyDataSetChanged()
                        statusNetwork = false
                    }
                    Status.ERROR -> statusNetwork = true
                    Status.LOADING -> {}
                }
            }
        }
    }

    private fun setInitNetworkErrorLayout(status: Boolean) {
        when (status) {
            true -> {
                progressDialog.dismiss()
                Utils.snackBarErrorConnection(requireView(), requireContext())
                binding.constraintDetailError.constraintNetworkError.visibility = View.VISIBLE
                binding.constraintDetail.visibility = View.GONE
            }
            false -> {
                binding.constraintDetailError.constraintNetworkError.visibility = View.GONE
                binding.constraintDetail.visibility = View.VISIBLE
            }
        }

    }

    private fun setDetailAttr(detailResponse: DetailResponse) {
        progressDialog.dismiss()
        val userScore = detailResponse.voteAverage * 10
        with(binding) {
            progressBarDetailContentUserScore.progress = userScore.toInt()
            tvDetailContentUserScore.text = userScore.toString()
            tvDetailContentTagline.text = detailResponse.tagline
            tvDetailContentGenre.text = Utils.insertStringGenre(detailResponse.genres)
            tvDetailContentOverview.text = detailResponse.overview
            context?.let {
                Glide.with(it)
                    .load(ApiConstant.IMAGE_PATH.plus(detailResponse.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading)
                            .error(R.drawable.ic_baseline_error)
                    )
                    .into(detailContentImage)
            }
        }
    }

    private fun setDetailAttrMovie(detailResponse: DetailResponse) {
        title = detailResponse.title
        with(binding) {
            tvDetailContentTitle.text = detailResponse.originalTitle
            tvDetailContentReleasedDate.text = detailResponse.releaseDate
            tvDetailContentDuration.text = detailResponse.runtime.toString().plus("m")
        }
    }

    private fun setDetailAttrTvShow(detailResponse: DetailResponse) {
        title = detailResponse.name
        with(binding) {
            tvDetailContentTitle.text = detailResponse.name
            tvDetailContentReleasedDate.text = detailResponse.firstAirDate
            tvDetailContentDuration.visibility = View.GONE
            bullets1DetailContent.visibility = View.GONE
        }
    }


    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
        }
    }

    //for instrument testing please comment the onAttach and onDetach
    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNav()
    }*/

    /*override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showBottomNav()
    }*/

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_detail_content_trailer ->
                view?.findNavController()?.navigate(
                    DetailCinemaFragmentDirections.actionDetailCinemaFragmentToBottomSheetVideosFragment(
                        extraId,
                        title,
                        extraType
                    )
                )
            R.id.btn_network_error_try_again -> setInit()
        }
    }

    private fun backPressedFragment() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.findNavController()?.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}