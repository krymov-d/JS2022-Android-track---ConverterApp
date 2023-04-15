package kz.kd.converterapp.data

import kz.kd.converterapp.domain.Photo
import kz.kd.converterapp.domain.PhotosRepository

class PhotosRepositoryImpl(private val mainApi: MainApi) : PhotosRepository {
    override suspend fun getPhotos(): List<Photo> {
        return mainApi.getAllPhotos()
    }

    override suspend fun getPhotoById(id: Int): Photo {
        return mainApi.getPhotoById(id)
    }
}