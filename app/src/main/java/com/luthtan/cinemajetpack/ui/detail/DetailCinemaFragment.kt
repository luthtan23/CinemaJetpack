package com.luthtan.cinemajetpack.ui.detail

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.DetailCinemaFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.local.DetailWithRecommendation
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
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
    private val args: DetailCinemaFragmentArgs by navArgs()

    private var _binding: DetailCinemaFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private var extraId: Int = 0
    private var extraType = ""
    private var title = "title"

    private var statusNetwork: Boolean = false
    private var isCinema: Boolean = false

    private lateinit var detailEntity: DetailEntity

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

        isCinema = extraType == Constant.TYPE_MOVIE

        staringAdapter = StaringAdapter()
        recommendationAdapter = RecommendationAdapter()

        setInit()

        binding.btnDetailContentTrailer.setOnClickListener(this)
        binding.ibDetailContentFavorite.setOnClickListener(this)
        binding.constraintDetailError.btnNetworkErrorTryAgain.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setInit() {
        progressDialog.show()
        detailViewModel.setExtraId(extraId)
        getData(isCinema)
        setAdapter()
    }

    private fun getData(isCinema: Boolean) {
        backPressedFragment()
        if (isCinema) {
            detailViewModel.detailMovieFavorite.observe(viewLifecycleOwner, detailResponse)
            detailViewModel.detailWithCast.observe(viewLifecycleOwner, creditResponse)
            detailViewModel.detailWithRecommendation.observe(
                viewLifecycleOwner,
                recommendationResponse
            )
            detailViewModel.detailWithTrailer.observe(
                viewLifecycleOwner,
                trailerResponse
            )
        } else {
            detailViewModel.detailTvShowFavorite.observe(viewLifecycleOwner, detailResponse)
            detailViewModel.detailWithCastTvShow.observe(viewLifecycleOwner, creditResponse)
            detailViewModel.detailWithRecommendationTvShow.observe(
                viewLifecycleOwner,
                recommendationResponse
            )
            detailViewModel.detailWithTrailerTvShow.observe(viewLifecycleOwner, trailerResponse)
        }
    }

    private fun setAdapter() {
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

    private val detailResponse: Observer<Resource<DetailEntity>> by lazy {
        Observer<Resource<DetailEntity>> { detailResponse ->
            if (detailResponse != null) {
                when (detailResponse.status) {
                    Status.SUCCESS -> {
                        detailEntity = detailResponse.data!!
                        setDetailAttr(detailResponse.data)
                        if (isCinema) {
                            setDetailAttrMovie(detailResponse.data)
                            setFavoriteButtonStatus(detailResponse.data.isMovieFavorite)
                        } else {
                            setDetailAttrTvShow(detailResponse.data)
                            setFavoriteButtonStatus(detailResponse.data.isTvShowFavorite)
                        }
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

    private val creditResponse: Observer<Resource<DetailWithCast>> by lazy {
        Observer<Resource<DetailWithCast>> { creditResponse ->
            if (creditResponse != null) {
                when (creditResponse.status) {
                    Status.SUCCESS -> {
                        if (creditResponse.data != null) {
                            staringAdapter.setStaring(creditResponse.data.castItemEntity)
                            staringAdapter.notifyDataSetChanged()
                            statusNetwork = false
                        }
                        progressDialog.dismiss()
                    }
                    Status.ERROR -> {
                        progressDialog.dismiss()
                        statusNetwork = true
                    }
                    Status.LOADING -> {
                    }
                }
                setInitNetworkErrorLayout(statusNetwork)
            }
        }
    }

    private val recommendationResponse: Observer<Resource<DetailWithRecommendation>> by lazy {
        Observer<Resource<DetailWithRecommendation>> { recommendationResponse ->
            if (recommendationResponse != null) {
                when (recommendationResponse.status) {
                    Status.SUCCESS -> {
                        recommendationAdapter.setRecommendation(
                            recommendationResponse.data!!.recommendationItemsEntity,
                            Constant.TYPE_MOVIE
                        )
                        staringAdapter.notifyDataSetChanged()
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

    private val trailerResponse: Observer<Resource<DetailWithTrailer>> by lazy {
        Observer<Resource<DetailWithTrailer>> { trailerResponse ->
            if (trailerResponse != null) {
                when (trailerResponse.status) {
                    Status.SUCCESS -> {
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
                binding.constraintDetailError.constraintNetworkError.visibility = View.VISIBLE
                binding.constraintDetail.visibility = View.GONE
            }
            false -> {
                binding.constraintDetailError.constraintNetworkError.visibility = View.GONE
                binding.constraintDetail.visibility = View.VISIBLE
            }
        }

    }

    private fun setDetailAttr(detailResponse: DetailEntity) {
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

    private fun setDetailAttrMovie(detailResponse: DetailEntity) {
        title = detailResponse.title
        with(binding) {
            tvDetailContentTitle.text = detailResponse.originalTitle
            tvDetailContentReleasedDate.text = detailResponse.releaseDate
            tvDetailContentDuration.text = detailResponse.runtime.toString().plus("m")
        }
    }

    private fun setDetailAttrTvShow(detailResponse: DetailEntity) {
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
            R.id.ib_detail_content_favorite -> setFavoriteStatus(isCinema)
        }
    }

    private fun setFavoriteStatus(isCinema: Boolean) {
        try {
            if (isCinema) detailViewModel.setMovieFavorite()
            else detailViewModel.setTvShowFavorite()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun setFavoriteButtonStatus(isFavorite: Boolean) {
        if (isFavorite) binding.ibDetailContentFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite))
        else binding.ibDetailContentFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_border))
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