package com.pk.hangup.mainUI.fragments.upload

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pk.hangup.R

class UploadPostFragment : Fragment() {

    companion object {
        fun newInstance() = UploadPostFragment()
    }

    private lateinit var viewModel: UploadPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upload_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UploadPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}