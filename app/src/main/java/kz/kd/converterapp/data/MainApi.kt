package kz.kd.converterapp.data

import kz.kd.converterapp.domain.Photo
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("photos")
    suspend fun getAllPhotos(): List<Photo>

    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") photoId: Int): Photo
}