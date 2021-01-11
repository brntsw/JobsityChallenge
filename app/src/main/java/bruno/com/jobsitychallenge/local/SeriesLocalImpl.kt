package bruno.com.jobsitychallenge.local

import bruno.com.jobsitychallenge.MainApplication
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.data.*
import bruno.com.jobsitychallenge.data.model.EpisodeResponse
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.data.model.SerieSearchResponse
import bruno.com.jobsitychallenge.utils.FileUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Error
import java.lang.Exception

class SeriesLocalImpl() : ISeriesRepository {

    override suspend fun getSeries() : Flow<List<SerieResponse>> {
        return flow {
            val jsonFileString = FileUtils.getJsonFromAsset("series.json")

            val gson = Gson()
            val listSeriesType = object : TypeToken<List<SerieResponse>>() {}.type

            emit(gson.fromJson(jsonFileString, listSeriesType))
        }
    }

    override suspend fun getSerieDetails(serieId: Long) : Flow<SerieResponse> {
        return flow {
            val jsonFileString = FileUtils.getJsonFromAsset("serie_details.json")

            val gson = Gson()
            val serieType = object : TypeToken<SerieResponse>(){}.type

            emit(gson.fromJson(jsonFileString, serieType))
        }
    }

    override suspend fun searchSerieByName(name: String) : Flow<List<SerieSearchResponse>> {
        return flow {
            val jsonFileString = FileUtils.getJsonFromAsset("serie_search.json")

            val gson = Gson()
            val listSeriesType = object : TypeToken<List<SerieSearchResponse>>(){}.type

            emit(gson.fromJson(jsonFileString, listSeriesType))
        }
    }

    override suspend fun getEpisodes(serieId: Long) : Flow<List<EpisodeResponse>> {
        return flow {
            val jsonFileString = FileUtils.getJsonFromAsset("episodes250.json")

            val gson = Gson()
            val listEpisodesType = object : TypeToken<List<EpisodeResponse>>(){}.type

            emit(gson.fromJson(jsonFileString, listEpisodesType))
        }
    }
}