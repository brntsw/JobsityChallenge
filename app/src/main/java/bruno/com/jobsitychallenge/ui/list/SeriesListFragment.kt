package bruno.com.jobsitychallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.extension.setToolbar
import bruno.com.jobsitychallenge.local.SeriesLocalImpl
import bruno.com.jobsitychallenge.remote.SeriesRemoteImpl
import bruno.com.jobsitychallenge.ui.adapter.SeriesAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject
import timber.log.Timber

class SeriesListFragment : Fragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerSeries: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var shimmer: ShimmerFrameLayout

    private lateinit var viewModel: SeriesListViewModel
    private lateinit var adapter: SeriesAdapter

    private val seriesLocalImpl: SeriesLocalImpl by inject()
    private val seriesRemoteImpl: SeriesRemoteImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            SeriesListViewModel.SeriesListViewModelFactory(seriesRemoteImpl)
        ).get(SeriesListViewModel::class.java)

        viewModel.getSeries()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_series_list, container, false)

        val gridLayoutManager = GridLayoutManager(activity, 2)
        recyclerSeries = view.findViewById(R.id.recycler_series)
        toolbar = view.findViewById(R.id.toolbar)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)
        shimmer = view.findViewById(R.id.shimmer_view)

        recyclerSeries.layoutManager = gridLayoutManager
        activity?.setToolbar(toolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar

        actionBar?.title = getString(R.string.app_title_list)

        swipeRefresh.setOnRefreshListener { viewModel.getSeries() }

        viewModel.series.observe(viewLifecycleOwner, { series ->
            adapter = SeriesAdapter(series)
            recyclerSeries.adapter = adapter
        })

        viewModel.loading.observe(viewLifecycleOwner, { displayLoading ->
            viewModel.handleProgress(displayLoading, shimmer, recyclerSeries, swipeRefresh)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            Timber.e("An error happened: $error")
        })

        return view
    }
}