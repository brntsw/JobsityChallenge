package bruno.com.jobsitychallenge.remote

import bruno.com.jobsitychallenge.data.IRequest
import bruno.com.jobsitychallenge.data.ISeriesRepository
import bruno.com.jobsitychallenge.data.model.EpisodeResponse
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.data.model.SerieSearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SeriesRemoteImpl(private val seriesService: SeriesService) : ISeriesRepository {
    override suspend fun getSeries(): Flow<List<SerieResponse>> {
        return flow {
            emit(seriesService.getSeries(1))
        }
    }

    override suspend fun getSerieDetails(serieId: Long) : Flow<SerieResponse> {
        return flow {
            emit(seriesService.getSerieDetails(serieId))
        }
    }

    override suspend fun searchSerieByName(name: String) : Flow<List<SerieSearchResponse>> {
        return flow {
            emit(seriesService.searchSerie(name))
        }
    }

    override suspend fun getEpisodes(serieId: Long) : Flow<List<EpisodeResponse>> {
        return flow {
            emit(seriesService.getEpisodes(serieId))
        }
    }
}