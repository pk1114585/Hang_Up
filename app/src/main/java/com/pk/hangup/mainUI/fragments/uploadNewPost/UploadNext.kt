package com.pk.hangup.mainUI.fragments.uploadNewPost

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.pk.hangup.R
import com.pk.hangup.databinding.FragmentUploadNextBinding


class UploadNext : Fragment() {
    private lateinit var binding:FragmentUploadNextBinding
    private lateinit var viewModel: UploadPostNextModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Upload next ","onCreateCalled")
        val application = requireNotNull(activity?.application)
        val factory = ModelFactoryNext(application)
        viewModel = ViewModelProvider(this,factory).get(UploadPostNextModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUploadNextBinding.inflate(inflater,container,false)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val imageUri = sharedPref!!.getString("uploadImageUri","non")
        Log.i("Upload next ","image uri $imageUri")

        val imageView:ImageView = binding.imageView2
        Glide.with(imageView).load(imageUri).into(imageView)

        binding.includeAppBar.idCloseView.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.uploadPostFragment).also {
                findNavController().popBackStack(R.id.uploadNext,true)
            }
        })
        binding.includeAppBar.idNextView.setOnClickListener(View.OnClickListener {
            val user =  FirebaseAuth.getInstance().currentUser?.uid?.let {
                viewModel.uploadPost(Post(
                    userID = it,
                    caption = binding.idWritePostCaption.text.toString(),
                    downloadUrl = null
                ),Uri.parse(imageUri))
            }

        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Upload next ","onDestroyCalled")
    }
}