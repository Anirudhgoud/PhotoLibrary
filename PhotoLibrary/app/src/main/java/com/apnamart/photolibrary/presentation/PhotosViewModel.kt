package com.apnamart.photolibrary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apnamart.photolibrary.common.Result
import com.apnamart.photolibrary.domain.model.PhotosApiResponse
import com.apnamart.photolibrary.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _photos = MutableLiveData<List<PhotosApiResponse>>()
    val photos = _photos


    fun getPhotos() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val photosResult = getPhotosUseCase.invoke()) {
                is Result.Success -> {
                    _photos.postValue(photosResult.data!!)
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(photosResult.exception.message)
                }
            }
        }
    }
}