package kz.kd.converterapp.domain

class GetPhotoByIdUseCase(private val photosRepository: PhotosRepository) {
    suspend operator fun invoke(id: Int): Photo {
        return photosRepository.getPhotoById(id)
    }
}