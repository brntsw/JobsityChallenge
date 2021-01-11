package bruno.com.jobsitychallenge.ui.details

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import bruno.com.jobsitychallenge.data.ISeriesRepository
import bruno.com.jobsitychallenge.data.mapper.SerieDetailsMapper
import bruno.com.jobsitychallenge.data.model.EpisodeResponse
import bruno.com.jobsitychallenge.data.model.SerieDetailsResponse
import bruno.com.jobsitychallenge.ui.list.SeriesListViewModel
import bruno.com.jobsitychallenge.ui.model.SerieDetailsView
import bruno.com.jobsitychallenge.ui.model.SerieView
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SerieDetailsViewModel(private val repository: ISeriesRepository) : ViewModel() {

    private val _loading = MutableLiveData(false)
    private val _serie = MutableLiveData<SerieDetailsView>()
    private val _errorMessage = MutableLiveData("")

    val loading: LiveData<Boolean> = _loading
    val serie: LiveData<SerieDetailsView> = _serie
    val errorMessage: LiveData<String> = _errorMessage

    fun getSerieDetails(id: Long) {
        _loading.value = false

        CoroutineScope(Dispatchers.Main).launch {
            repository.getSerieDetails(id)
                .zip(repository.getEpisodes(id)) { serieDetails, episodes ->
                    val allEpisodes = mutableListOf<EpisodeResponse>()
                    allEpisodes.addAll(episodes)

                    SerieDetailsResponse(
                        serieDetails.id,
                        serieDetails.name,
                        serieDetails.image.medium,
                        serieDetails.schedule.time,
                        serieDetails.schedule.days,
                        serieDetails.genres,
                        serieDetails.summary,
                        allEpisodes
                    )
                }
                .onStart { _loading.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .onEmpty { _errorMessage.postValue("Empty!") }
                .catch { e -> _errorMessage.postValue(e.message) }
                .collect { serieResponse ->
                    _serie.postValue(SerieDetailsMapper().mapFromRepoToView(serieResponse))
                }
        }
    }

    fun handleProgress(load: Boolean,
                       shimmer: ShimmerFrameLayout,
                       contentView: View
    ) {
        if(load) {
            shimmer.visibility = View.VISIBLE
            contentView.visibility = View.GONE
            shimmer.startShimmer()
        }
        else{
            shimmer.visibility = View.GONE
            contentView.visibility = View.VISIBLE
            shimmer.stopShimmer()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class SerieDetailsViewModelFactory(private val repository: ISeriesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SerieDetailsViewModel(repository) as T
        }

    }

}