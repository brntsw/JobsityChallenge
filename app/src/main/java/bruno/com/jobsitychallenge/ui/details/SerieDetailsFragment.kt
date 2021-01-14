package bruno.com.jobsitychallenge.ui.details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bruno.com.jobsitychallenge.databinding.FragmentSerieDetailsBinding
import bruno.com.jobsitychallenge.extension.toHtml
import bruno.com.jobsitychallenge.local.SeriesLocalImpl
import bruno.com.jobsitychallenge.remote.SeriesRemoteImpl
import bruno.com.jobsitychallenge.ui.adapter.SeasonsAdapter
import bruno.com.jobsitychallenge.utils.SerieDetailsUtils
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject
import timber.log.Timber

class SerieDetailsFragment : Fragment() {

    var serieId: Long = -1

    private lateinit var viewModel: SerieDetailsViewModel
    private lateinit var adapter: SeasonsAdapter

    private val seriesLocalImpl: SeriesLocalImpl by inject()
    private val seriesRemoteImpl: SeriesRemoteImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: SerieDetailsFragmentArgs by navArgs()
        serieId = args.serieId

        viewModel = ViewModelProvider(
            this,
            SerieDetailsViewModel.SerieDetailsViewModelFactory(seriesRemoteImpl)
        ).get(SerieDetailsViewModel::class.java)

        viewModel.getSerieDetails(serieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSerieDetailsBinding.inflate(inflater, container, false)

        binding.recyclerSeasons.layoutManager = LinearLayoutManager(activity)
        binding.recyclerSeasons.isNestedScrollingEnabled = false

        binding.toolbar.title = "Serie details"

        viewModel.serie.observe(viewLifecycleOwner, { serieDetails ->
            binding.serie = serieDetails
            binding.formattedGenres = SerieDetailsUtils.convertGenresToString(serieDetails.genres)
            binding.formattedTimeDays = SerieDetailsUtils.convertTimeAndDaysToString(serieDetails.time, serieDetails.daysAirs)
            serieDetails.summary?.toHtml()?.let { binding.summaryHtml = it }
            adapter = SeasonsAdapter(serieDetails.episodesBySeason)
            binding.recyclerSeasons.adapter = adapter
        })

        viewModel.loading.observe(viewLifecycleOwner, { displayLoading ->
            viewModel.handleProgress(displayLoading, binding.shimmerView, binding.nestedView)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            Timber.e("An error happened: $error")
        })

        return binding.root
    }
}