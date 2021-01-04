package bruno.com.jobsitychallenge.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bruno.com.jobsitychallenge.data.IRequest
import bruno.com.jobsitychallenge.data.ISeriesRepository
import bruno.com.jobsitychallenge.data.mapper.SeriesMapper
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.ui.model.SerieView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
            repository.getSeries(object : IRequest<List<SerieResponse>>{
                override fun onSuccess(result: List<SerieResponse>) {
                    _loading.postValue(false)
                    _series.postValue(result.map { SeriesMapper().mapFromRepoToView(it) })
                }

                override fun onError(message: String) {
                    _loading.postValue(false)
                    _errorMessage.postValue(message)
                }

            })
        }
    }

    @Suppress("UNCHECKED_CAST")
    class SeriesListViewModelFactory(private val repository: ISeriesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SeriesListViewModel(repository) as T
        }

    }

}