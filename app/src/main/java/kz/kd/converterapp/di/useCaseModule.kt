package kz.kd.converterapp.di

import kz.kd.converterapp.domain.GetPhotoByIdUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPhotoByIdUseCase(get()) }
}