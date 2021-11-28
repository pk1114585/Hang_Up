package com.pk.hangup.mainUI.fragments.uploadNewPost

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pk.hangup.R
import com.pk.hangup.databinding.UploadPostFragmentBinding
import com.pk.hangup.mainUI.resource.adapters.GalleryImageAdapter
import com.pk.hangup.mainUI.resource.adapters.GalleryImageSelectClickListener

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
                val adapter = it?.value?.let { it1 -> GalleryImageAdapter(it1,
                    GalleryImageSelectClickListener { selectedImageUri->
                        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        editor?.putString("uploadImageUri",selectedImageUri)
                        editor?.apply()
                        findNavController().navigate(R.id.uploadNext)
                    })}
                binding.idGalleyView.idGalleyRecyclerView.adapter= adapter
                binding.idGalleyView.idGalleyRecyclerView.layoutManager = GridLayoutManager(context,3)

            }
        })
        binding.idUploadFromWebCam.idImageFromCamera.setOnClickListener {
            startIntentForImageCap()
        }
        binding.idUploadPostAppBar.idCloseView.setOnClickListener {
            findNavController().navigate(R.id.postViewFragment)
            findNavController().popBackStack(R.id.uploadPostFragment,true)
        }
        binding.idUploadPostAppBar.idNextView.setOnClickListener {
            //findNavController().navigate(R.id.uploadNext)
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