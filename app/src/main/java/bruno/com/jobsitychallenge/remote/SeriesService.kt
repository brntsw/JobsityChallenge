package bruno.com.jobsitychallenge.remote

import bruno.com.jobsitychallenge.data.model.EpisodeResponse
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.data.model.SerieSearchResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesService {

    @GET("shows")
    suspend fun getSeries(@Query("page") pageNum: Int) : List<SerieResponse>

    @GET("shows/{id}")
    suspend fun getSerieDetails(@Path("id") id: Long) : SerieResponse

    @GET("search/shows")
    suspend fun searchSerie(@Query("q") name: String) : List<SerieSearchResponse>

    @GET("shows/{id}/episodes")
    suspend fun getEpisodes(@Path("id") id: Long) : List<EpisodeResponse>

    class Builder {
        fun makeSeriesService(okHttpClient: OkHttpClient) : SeriesService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.tvmaze.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(SeriesService::class.java)
        }
    }
}