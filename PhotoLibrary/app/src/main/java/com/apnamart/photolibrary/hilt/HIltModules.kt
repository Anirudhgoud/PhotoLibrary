package com.apnamart.photolibrary.hilt

import com.apnamart.photolibrary.data.api.PhotosApi
import com.apnamart.photolibrary.data.repository.PhotosRepositoryImpl
import com.apnamart.photolibrary.domain.repository.IPhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HIltModules {

    @Provides
    @Singleton
    fun provideAllPhotosPI(): PhotosApi {
        return Retrofit.Builder().baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PhotosApi::class.java)
    }

    @Provides
    fun provideAllPhotosRepository(mealSearchAPI: PhotosApi): IPhotosRepository {
        return PhotosRepositoryImpl(mealSearchAPI)
    }
}