package kz.kd.converterapp

import android.app.Application
import kz.kd.converterapp.di.networkModule
import kz.kd.converterapp.di.repositoryModule
import kz.kd.converterapp.di.useCaseModule
import kz.kd.converterapp.di.viewModelModule
import org.koin.core.context.startKoin

class ConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}