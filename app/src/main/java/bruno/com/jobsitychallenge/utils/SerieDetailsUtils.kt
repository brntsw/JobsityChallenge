package bruno.com.jobsitychallenge.utils

import bruno.com.jobsitychallenge.MainApplication
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.extension.listToWordsSeparatedByCommas

object SerieDetailsUtils {

    fun convertGenresToString(genres: List<String>) : String {
        return MainApplication.appContext.getString(R.string.genres_holder, genres.listToWordsSeparatedByCommas())
    }

    fun convertTimeAndDaysToString(time: String, days: List<String>) : String {
        return MainApplication.appContext.getString(R.string.time_days_holder, time, days.listToWordsSeparatedByCommas())
    }

}