package bruno.com.jobsitychallenge.utils

import android.content.Context
import android.net.ConnectivityManager
import bruno.com.jobsitychallenge.MainApplication

object ConnectivityUtils {

    fun isConnected(): Boolean {
        val activeNetwork = (MainApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            return true
        }

        return true
    }

}