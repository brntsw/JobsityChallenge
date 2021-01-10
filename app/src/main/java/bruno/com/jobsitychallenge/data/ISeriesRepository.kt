package bruno.com.jobsitychallenge.data

import bruno.com.jobsitychallenge.data.model.EpisodeResponse
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.data.model.SerieSearchResponse
import kotlinx.coroutines.flow.Flow

interface ISeriesRepository {

    suspend fun getSeries(): Flow<List<SerieResponse>>
    suspend fun getSerieDetails(serieId: Long, request: IRequest<SerieResponse>)
    suspend fun searchSerieByName(name: String, request: IRequest<List<SerieSearchResponse>>)
    suspend fun getEpisodes(serieId: Long, request: IRequest<List<EpisodeResponse>>)

}