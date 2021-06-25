package com.luthtan.cinemajetpack.ui.detail

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.BottomSheetVideosLayoutBinding
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.ui.detail.adapter.VideosAdapter
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.util.Utils
import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import com.luthtan.cinemajetpack.vo.Resource
import com.luthtan.cinemajetpack.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetVideosFragment : BottomSheetDialogFragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    private var _binding: BottomSheetVideosLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var videosAdapter: VideosAdapter

    private var title = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetVideosLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extraId = BottomSheetVideosFragmentArgs.fromBundle(arguments as Bundle).id
        val extraType = BottomSheetVideosFragmentArgs.fromBundle(arguments as Bundle).typeCinema
        title = BottomSheetVideosFragmentArgs.fromBundle(arguments as Bundle).title

        detailViewModel.setExtraId(extraId)

        videosAdapter = VideosAdapter()

        if (extraType == Constant.TYPE_MOVIE) {
        } else {
            detailViewModel.getDetailVideoTvShow(extraId)
        }

        detailViewModel.detailWithTrailer.observe(viewLifecycleOwner, trailerResponse)

        with(binding.rvDetailContentVideo) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = videosAdapter
        }
    }

    private val trailerResponse: Observer<Resource<DetailWithTrailer>> by lazy {
        Observer<Resource<DetailWithTrailer>> { trailerResponse ->
            progressDialog.dismiss()
            if (trailerResponse.data != null) {
                when (trailerResponse.status) {
                    Status.SUCCESS -> {
                        videosAdapter.setVideos(
                            requireActivity() as MainActivity,
                            trailerResponse.data.trailerItemsEntity
                        )
                        binding.tvBottomSheetVideoTitle.text =
                            title.plus(" ").plus(getString(R.string.videos))
                    }
                    Status.ERROR -> {
                        Utils.showAlertDialog(
                            requireContext(),
                            getString(R.string.title_alert_failed_login),
                            getString(R.string.message_alert_failed_login)
                        )
                        return@Observer
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
        }
    }
}