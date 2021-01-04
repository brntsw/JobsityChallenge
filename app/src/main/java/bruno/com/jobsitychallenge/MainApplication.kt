package bruno.com.jobsitychallenge

import android.app.Application
import android.content.Context
import bruno.com.jobsitychallenge.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                applicationModule
            )
        }
    }

    companion object {
        lateinit  var appContext: Context
    }

}