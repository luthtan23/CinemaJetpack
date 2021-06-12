package com.luthtan.cinemajetpack.ui.detail

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.DetailCinemaFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationItems
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.ui.detail.adapter.RecommendationAdapter
import com.luthtan.cinemajetpack.ui.detail.adapter.StaringAdapter
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.util.Utils
import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCinemaFragment : Fragment(), View.OnClickListener {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var staringAdapter: StaringAdapter
    private lateinit var recommendationAdapter: RecommendationAdapter

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

        extraId = DetailCinemaFragmentArgs.fromBundle(arguments as Bundle).id
        extraType = DetailCinemaFragmentArgs.fromBundle(arguments as Bundle).typeCinema

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
    }

    private fun getData() {
        backPressedFragment()


        detailViewModel.detailResponse.observe(viewLifecycleOwner, { detailResponse ->
            if (detailResponse != null) {
                setDetailAttr(detailResponse)
                if (extraType == Constant.TYPE_MOVIE) {
                    setDetailAttrMovie(detailResponse)
                } else {
                    setDetailAttrTvShow(detailResponse)
                }
            }
        })

        detailViewModel.errorResponse.observe(viewLifecycleOwner, { errorResponse ->
            if (errorResponse != null) {
                progressDialog.dismiss()
                Utils.snackBarErrorConnection(requireView(), requireContext())
                if (!statusNetwork) {
                    setInitNetworkErrorLayout(true)
                }
            }  else setInitNetworkErrorLayout(false)
        })

        detailViewModel.creditResponse.observe(viewLifecycleOwner, { creditsResponse ->
            if (creditsResponse != null) {
                progressDialog.dismiss()
                setDetailCredits(creditsResponse.cast)
            }
        })

        detailViewModel.recommendationResponse.observe(viewLifecycleOwner, { recommendationResponse ->
            if (recommendationResponse != null) {
                progressDialog.dismiss()
                setDetailRecommendation(recommendationResponse.results!!)
            }
        })

    }

    private fun setInitNetworkErrorLayout(status: Boolean) {
        when(status) {
            true -> {
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
        val userScore = detailResponse.voteAverage*10
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
                            .error(R.drawable.ic_baseline_error))
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

    private fun setDetailCredits(cast: List<CastItem>) {
        staringAdapter.setStaring(cast)
        with(binding.rvDetailContentStaring) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = staringAdapter
        }
    }

    private fun setDetailRecommendation(results: List<RecommendationItems>) {
        recommendationAdapter.setRecommendation(results, Constant.TYPE_MOVIE)
        with(binding.rvDetailContentRecommendation) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = recommendationAdapter
        }
    }

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNav()
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showBottomNav()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_detail_content_trailer ->
                view?.findNavController()?.navigate(DetailCinemaFragmentDirections.actionDetailCinemaFragmentToBottomSheetVideosFragment(extraId, title, extraType))
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