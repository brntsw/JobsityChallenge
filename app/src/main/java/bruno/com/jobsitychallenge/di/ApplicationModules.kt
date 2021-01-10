package bruno.com.jobsitychallenge.di

import android.util.Pair
import bruno.com.jobsitychallenge.local.SeriesLocalImpl
import bruno.com.jobsitychallenge.remote.SeriesRemoteImpl
import bruno.com.jobsitychallenge.remote.SeriesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import timber.log.Timber
import java.util.ArrayList
import java.util.concurrent.TimeUnit

val applicationModule = module {
    single { SeriesLocalImpl() }
    single { SeriesRemoteImpl(get()) }
}

val networkModule = module {
    factory { provideLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideSeriesService(get()) }
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)

    return builder.build()
}

fun provideLoggingInterceptor() : HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor { message: String? ->
        Timber.tag("OkHttp").d(message)
    }
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideSeriesService(okHttpClient: OkHttpClient): SeriesService {
    return SeriesService.Builder().makeSeriesService(okHttpClient)
}