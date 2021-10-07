package com.pk.hangup.mainUI.fragments.uploadNewPost

import android.app.Application
import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pk.hangup.mainUI.resource.adapters.ImageData
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class UploadPostViewModel(private val contentResolver: ContentResolver?) : ViewModel(),CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main

    private var imagesLiveData: MutableLiveData<MutableList<ImageData>> = MutableLiveData()
    fun getImageList(): MutableLiveData<MutableList<ImageData>> {
        return imagesLiveData
    }
    private fun loadImageFromSDCard():MutableList<ImageData>
    {
        val imageList = mutableListOf<ImageData>()
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        val projection  = arrayOf(
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media._ID
        )
        val selection = "${MediaStore.Images.Media.DATE_ADDED} >= ?"
        val selectionArg = arrayOf(
            getDaysAgo(-30)  // 1 week ago
        )

        //  4. how to sort: ASC or DESC
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val query = contentResolver?.query(collection,projection,selection,selectionArg,sortOrder)
        Log.i("upload view model",query?.count.toString())
        query?.use { cursor ->
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext())
            {
                val name = cursor.getString(nameColumn)
                val id = cursor.getLong(idColumn)

                val contentUri:Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id
                )
                imageList.add(ImageData(name,contentUri.toString()))
                Log.i("upload view model",name)
            }
        }
        return imageList
    }
    fun getAllImages()
    {
        viewModelScope.launch{
            imagesLiveData.value = withContext(Dispatchers.IO)
            {
                Log.i("upload view model","query called")
                loadImageFromSDCard()
            }
        }
    }
    private fun getDaysAgo(ago: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, ago)
        val date: Int = (calendar.timeInMillis / 1000).toInt()
        return date.toString()
    }
}