package com.eco4ndly.awesomeimageviewer.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco4ndly.awesomeimageviewer.data.PhotoListRepository
import com.eco4ndly.awesomeimageviewer.infra.cancelIfActive
import com.eco4ndly.awesomeimageviewer.infra.network.ApiResult
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo
import com.eco4ndly.weirdsms.infra.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A Sayan Porya code on 2020-01-19
 */
class MainActivityViewModel @Inject constructor(
    private val photoListRepository: PhotoListRepository
) : ViewModel() {

    private val photoListMutableLiveData = MutableLiveData<List<Photo>>()
    val photoListLiveData: LiveData<List<Photo>> = photoListMutableLiveData

    private val showErrorDialogMutableLiveData = SingleLiveEvent<String>()
    val showErrorDialogLiveData: LiveData<String> = showErrorDialogMutableLiveData

    private val showLoaderMutableLiveData = SingleLiveEvent<Boolean>()
    val showLoaderLiveData: LiveData<Boolean> = showLoaderMutableLiveData

    private var getPhotosJob: Job? = null

    val imageDetailsFragmentDataSet by lazy {
        ImageDetailsFragmentDataSet(this)
    }



    fun getPhotos() {
        getPhotosJob?.cancelIfActive()
        getPhotosJob = viewModelScope.launch {
            photoListRepository.getPhotos().collect {
                when (it) {
                    is ApiResult.Success<List<Photo>> -> photoListMutableLiveData.value = it.data
                    is ApiResult.Error -> showErrorDialogMutableLiveData.value = it.message
                    is ApiResult.Loading -> showLoaderMutableLiveData.value = it.isLoading
                }
            }
        }
    }


    inner class ImageDetailsFragmentDataSet constructor(private  val viewModel: MainActivityViewModel) {
        var currentImagePosition = 0
    }

}