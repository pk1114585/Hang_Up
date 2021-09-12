package com.pk.hangup.mainUI.fragments.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pk.hangup.R
import com.pk.hangup.databinding.PostViewFragmentBinding

class PostViewFragment : Fragment() {
    private lateinit var binding:PostViewFragmentBinding
    private lateinit var viewModel: PostViewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.post_view_fragment,container,false)
        viewModel = ViewModelProvider(this).get(PostViewViewModel::class.java)

        val mAdapter = PostAdapter(mutableListOf(
            Users("Somesh"),
            Users("pradeep"),
            Users("pk")
        ))
        binding.idStoriesList.adapter = StoriesAdapter()
        binding.idStoriesList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.idPostList.layoutManager = LinearLayoutManager(context)
        binding.idPostList.adapter = mAdapter
        binding.lifecycleOwner = this
        return binding.root
    }
}