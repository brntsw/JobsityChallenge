package bruno.com.jobsitychallenge.remote

import bruno.com.jobsitychallenge.data.IRequest
import bruno.com.jobsitychallenge.data.ISeriesRepository
import bruno.com.jobsitychallenge.data.model.EpisodeResponse
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.data.model.SerieSearchResponse

class SeriesRemoteImpl : ISeriesRepository {
    override suspend fun getSeries(request: IRequest<List<SerieResponse>>) {
        TODO("Not yet implemented")
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