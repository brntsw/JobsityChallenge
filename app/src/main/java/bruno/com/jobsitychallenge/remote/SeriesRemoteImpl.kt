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

    override suspend fun getSerieDetails(serieId: Long, request: IRequest<SerieResponse>) {
        TODO("Not yet implemented")
    }

    override suspend fun searchSerieByName(
        name: String,
        request: IRequest<List<SerieSearchResponse>>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodes(serieId: Long, request: IRequest<List<EpisodeResponse>>) {
        TODO("Not yet implemented")
    }
}