package com.luthtan.cinemajetpack.ui.detail

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.BottomSheetVideosLayoutBinding
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerItems
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.ui.detail.adapter.VideosAdapter
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetVideosFragment : BottomSheetDialogFragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    private var _binding: BottomSheetVideosLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var videosAdapter: VideosAdapter

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
        val title = BottomSheetVideosFragmentArgs.fromBundle(arguments as Bundle).title
        val extraType = BottomSheetVideosFragmentArgs.fromBundle(arguments as Bundle).typeCinema

        videosAdapter = VideosAdapter()

        if (extraType == Constant.TYPE_MOVIE) {
            detailViewModel.getDetailVideoMovie(extraId)
        } else {
            detailViewModel.getDetailVideoTvShow(extraId)
        }

        detailViewModel.trailerResponse.observe(viewLifecycleOwner, { trailerResponse ->
            if (trailerResponse != null) {
                progressDialog.dismiss()
                setDetailTrailer(trailerResponse.results)
                binding.tvBottomSheetVideoTitle.text =
                    title.plus(" ").plus(getString(R.string.videos))
            }
        })
    }

    private fun setDetailTrailer(videos: List<TrailerItems>) {
        videosAdapter.setVideos(requireActivity() as MainActivity, videos)
        with(binding.rvDetailContentVideo) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = videosAdapter
        }

    }

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
        }
    }
}