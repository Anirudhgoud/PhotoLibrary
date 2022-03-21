package com.apnamart.photolibrary.domain.usecase

import com.apnamart.photolibrary.domain.repository.IPhotosRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val photosRepository: IPhotosRepository) {

    suspend operator fun invoke() = photosRepository.getAllPhotos()
}