package bruno.com.jobsitychallenge.di

import bruno.com.jobsitychallenge.local.SeriesLocalImpl
import org.koin.dsl.module

val applicationModule = module {
    single { SeriesLocalImpl() }
}