package kz.kd.converterapp.di

import kz.kd.converterapp.data.PhotosRepositoryImpl
import kz.kd.converterapp.domain.PhotosRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<PhotosRepository> { PhotosRepositoryImpl(get()) }
}