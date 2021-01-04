package bruno.com.jobsitychallenge.utils

import bruno.com.jobsitychallenge.MainApplication
import java.io.IOException

class FileUtils {
    companion object {
        fun getJsonFromAsset(fileName: String) : String? {
            val jsonString: String
            try {
                jsonString = MainApplication.appContext.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }
    }
}