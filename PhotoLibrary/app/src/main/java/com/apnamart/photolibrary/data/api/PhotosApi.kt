package com.apnamart.photolibrary.data.api

import com.apnamart.photolibrary.domain.model.PhotosApiResponse
import retrofit2.Response
import retrofit2.http.GET


interface PhotosApi {
    @GET("list")
    suspend fun getPhotos(): Response<List<PhotosApiResponse>>
}