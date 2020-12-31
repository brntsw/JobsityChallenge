package bruno.com.jobsitychallenge.data

interface ISeriesRepository {

    suspend fun getSeries(request: IRequest<List<SerieResponse>>)
    suspend fun getSerieDetails(serieId: Long, request: IRequest<SerieResponse>)
    suspend fun searchSerieByName(name: String, request: IRequest<List<SerieSearchResponse>>)
    suspend fun getEpisodes(serieId: Long, request: IRequest<List<EpisodeResponse>>)

}