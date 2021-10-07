package com.pk.hangup.mainUI.fragments.uploadNewPost

import android.annotation.SuppressLint
import android.app.Activity
import android.content.EntityIterator
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.pk.hangup.R
import com.pk.hangup.databinding.UploadPostFragmentBinding
import com.pk.hangup.mainUI.resource.adapters.GalleryImageAdapter
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UploadPostFragment : Fragment() {
    private lateinit var viewModel: UploadPostViewModel
    private lateinit var binding:UploadPostFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UploadPostFragmentBinding.inflate(inflater,container,false)
        val application = requireNotNull(activity?.application)
        val modelFactory = ModelFactory(activity?.contentResolver)
        viewModel = ViewModelProvider(this,modelFactory).get(UploadPostViewModel::class.java)
        viewModel.getAllImages()
        Log.i("Upload Fragment ",viewModel.getImageList().value?.size.toString())

        viewModel.getImageList().observe(viewLifecycleOwner, Observer {
            viewModel.getImageList().let {
                val adapter = it?.value?.let { it1 -> GalleryImageAdapter(it1) }
                binding.idGalleyView.idGalleyRecyclerView.adapter = adapter
                binding.idGalleyView.idGalleyRecyclerView.layoutManager = GridLayoutManager(context,3)

            }
        })
        binding.idUploadFromWebCam.idImageFromCamera.setOnClickListener {
            startIntentForImageCap()
        }
        binding.idUploadPostAppBar.idCloseView.setOnClickListener {
            findNavController().navigate(R.id.postViewFragment)
        }
        binding.idUploadPostAppBar.idNextView.setOnClickListener {

        }
        return binding.root
    }

    private fun startIntentForImageCap()
    {
        val intent  =  Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {

        }
        cameraResultLauncher.launch(intent)
    }
    private val cameraResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.data!=null) {
            val data: Bitmap? = result.data!!.getParcelableExtra("data")
            if (data != null) {
                binding.idUploadFromWebCam.idImageFromCamera.setImageBitmap(data)
            }else
                Log.i("main activity ","image not loaded ")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}