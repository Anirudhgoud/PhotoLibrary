package com.apnamart.photolibrary.data.repository

import com.apnamart.photolibrary.data.api.PhotosApi
import com.apnamart.photolibrary.domain.model.PhotosApiResponse
import com.apnamart.photolibrary.domain.repository.IPhotosRepository
import com.apnamart.photolibrary.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotosRepositoryImpl(private val api: PhotosApi) : IPhotosRepository {
    override suspend fun getAllPhotos(): Result<List<PhotosApiResponse>>  =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getPhotos()
                if (response.isSuccessful) {
                    return@withContext Result.Success(response.body()!!)
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}