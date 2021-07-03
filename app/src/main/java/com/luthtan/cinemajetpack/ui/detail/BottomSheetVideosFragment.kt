package com.luthtan.cinemajetpack.ui.detail

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.BottomSheetVideosLayoutBinding
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
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

        if (extraType == Constant.TYPE_MOVIE) detailViewModel.detailWithTrailer.observe(
            viewLifecycleOwner,
            trailerResponse
        )
        else detailViewModel.detailWithTrailerTvShow.observe(viewLifecycleOwner, trailerResponse)

        with(binding.rvDetailContentVideo) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
            adapter = videosAdapter
        }


    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        setBottomSheetHeight(dialog!!)
    }

    private val trailerResponse: Observer<Resource<DetailWithTrailer>> by lazy {
        Observer<Resource<DetailWithTrailer>> { trailerResponse ->
            progressDialog.dismiss()
            if (trailerResponse.data != null) {
                when (trailerResponse.status) {
                    Status.SUCCESS -> {
                        val expandableArray = ArrayList<Boolean>()
                        for (i in trailerResponse.data.trailerItemsEntity.indices) {
                            expandableArray.add(false)
                        }
                        videosAdapter.setVideos(
                            requireActivity() as MainActivity,
                            trailerResponse.data.trailerItemsEntity,
                            expandableArray
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

    private fun setBottomSheetHeight(dialog: Dialog) {
        val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?

        binding.bottomSheetVideoLayout.post {
            val newHeight = ViewGroup.LayoutParams.WRAP_CONTENT
            val viewGroupLayoutParams = bottomSheet!!.layoutParams
            viewGroupLayoutParams.height = newHeight
            bottomSheet.layoutParams = viewGroupLayoutParams
        }
    }

}