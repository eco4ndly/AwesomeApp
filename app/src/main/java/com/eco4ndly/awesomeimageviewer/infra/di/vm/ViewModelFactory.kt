package com.eco4ndly.awesomeimageviewer.infra.di.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * A Sayan Porya code on 2020-01-18
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory<V> constructor(private val viewModel: V) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return viewModel as T
  }

}