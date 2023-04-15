package kz.kd.converterapp.domain

interface PhotosRepository {
    suspend fun getPhotos(): List<Photo>
    suspend fun getPhotoById(id: Int): Photo
}