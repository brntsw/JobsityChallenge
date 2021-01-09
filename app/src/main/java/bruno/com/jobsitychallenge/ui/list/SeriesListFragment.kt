package bruno.com.jobsitychallenge.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.extension.setToolbar
import bruno.com.jobsitychallenge.local.SeriesLocalImpl
import bruno.com.jobsitychallenge.ui.adapter.SeriesAdapter
import bruno.com.jobsitychallenge.ui.model.SerieView
import org.koin.android.ext.android.inject

class SeriesListFragment : Fragment(), SerieItemClickListener {
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerSeries: RecyclerView

    private lateinit var viewModel: SeriesListViewModel
    private lateinit var adapter: SeriesAdapter

    private val seriesLocalImpl: SeriesLocalImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            SeriesListViewModel.SeriesListViewModelFactory(seriesLocalImpl)
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

        recyclerSeries.layoutManager = gridLayoutManager
        activity?.setToolbar(toolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar

        actionBar?.title = getString(R.string.app_title_list)

        viewModel.series.observe(viewLifecycleOwner, { series ->
            adapter = SeriesAdapter(series, this)
            recyclerSeries.adapter = adapter
        })

        viewModel.loading.observe(viewLifecycleOwner, { displayLoading ->
            //TODO
            Log.d("Loading", "The display value is $displayLoading")
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SeriesListFragment()
    }

    override fun onSerieClicked(serie: SerieView) {
        //TODO
        Toast.makeText(activity, "Serie details", Toast.LENGTH_LONG).show()
    }
}