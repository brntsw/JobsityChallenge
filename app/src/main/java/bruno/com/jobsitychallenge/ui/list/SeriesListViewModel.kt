package bruno.com.jobsitychallenge.ui.list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import bruno.com.jobsitychallenge.data.IRequest
import bruno.com.jobsitychallenge.data.ISeriesRepository
import bruno.com.jobsitychallenge.data.mapper.SeriesMapper
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.ui.model.SerieView
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SeriesListViewModel(private val repository: ISeriesRepository) : ViewModel() {

    private val _loading = MutableLiveData(false)
    private val _series = MutableLiveData<List<SerieView>>()
    private val _errorMessage = MutableLiveData("")

    val loading: LiveData<Boolean> = _loading
    val series: LiveData<List<SerieView>> = _series
    val errorMessage: LiveData<String> = _errorMessage

    fun getSeries() {
        _loading.value = true

        CoroutineScope(Dispatchers.Main).launch {
            repository.getSeries()
                .onStart { _loading.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .onEmpty { _errorMessage.postValue("Empty!") }
                .catch { e -> _errorMessage.postValue(e.message) }
                .collect { serieResponse ->
                    _series.postValue(serieResponse.map { SeriesMapper().mapFromRepoToView(it) })
                }
        }
    }

    fun handleProgress(load: Boolean,
                       shimmer: ShimmerFrameLayout,
                       recyclerView: RecyclerView,
                       swipeRefresh: SwipeRefreshLayout) {
        if(load) {
            shimmer.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            shimmer.startShimmer()
            swipeRefresh.isEnabled = false
        }
        else{
            shimmer.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            shimmer.stopShimmer()
            if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
            swipeRefresh.isEnabled = true
        }
    }

    @Suppress("UNCHECKED_CAST")
    class SeriesListViewModelFactory(private val repository: ISeriesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SeriesListViewModel(repository) as T
        }

    }

}