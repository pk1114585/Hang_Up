package com.pk.hangup.mainUI.fragments.uploadNewPost

import android.app.Application
import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ModelFactory(private val contentResolver: ContentResolver?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UploadPostViewModel::class.java))
            return UploadPostViewModel(contentResolver) as T
        else
            throw  IllegalArgumentException("view model class not found")
    }
}
class ModelFactoryNext(private val application: Application?):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UploadPostNextModel::class.java))
            return UploadPostNextModel(application) as T
        else
            throw IllegalArgumentException("Model not found")
    }

}