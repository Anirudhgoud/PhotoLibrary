package com.apnamart.photolibrary.domain.repository

import com.apnamart.photolibrary.common.Result
import com.apnamart.photolibrary.domain.model.PhotosApiResponse

interface IPhotosRepository {
    suspend fun getAllPhotos(): Result<List<PhotosApiResponse>>
}