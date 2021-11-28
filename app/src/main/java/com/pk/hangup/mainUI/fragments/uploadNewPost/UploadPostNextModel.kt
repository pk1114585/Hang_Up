package com.pk.hangup.mainUI.fragments.uploadNewPost

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

class UploadPostNextModel(private val application: Application?) : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase  = FirebaseDatabase.getInstance()
    private val firebaseStorage =  FirebaseStorage.getInstance()
    private val uploadStatus:LiveData<Boolean>
        get() = uploadStatus


    fun uploadPost(post:Post,uri: Uri)
    {
        viewModelScope.launch {
            upload(post,uri)
        }
    }
    private fun upload(uploadPost:Post,uri: Uri)
    {
        val userID = firebaseAuth.currentUser?.uid
        userID.let {
            val storageRef = firebaseStorage.reference.child("userPosts")
            storageRef.child("${uri.lastPathSegment}$userID").putFile(uri)
                .addOnCompleteListener{
                    if (it.isSuccessful)
                    {
                        val downloadUrl = storageRef.downloadUrl.toString()
                        uploadPost.downloadUrl = downloadUrl
                        val key = firebaseDatabase.reference.child("posts").push().key
                        if (key != null) {
                            firebaseDatabase.reference.child("posts").child(key).setValue(uploadPost)
                       }
                    }
                }
        }
    }
}