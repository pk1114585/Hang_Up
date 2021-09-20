package com.pk.hangup.mainUI.fragments.uploadNewPost

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pk.hangup.databinding.UploadPostFragmentBinding
import com.pk.hangup.mainUI.resource.adapters.GalleryImageAdapter

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
        return binding.root
    }

}