package com.pk.hangup.mainUI.fragments.posts

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PostViewModelFactory(private val application:Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewViewModel::class.java)) {
            return PostViewViewModel(application) as T
        }else {
            throw IllegalArgumentException("unknown model class")
        }
    }
}