package com.pk.hangup.mainUI.fragments.posts

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        binding.idStoriesList.addItemDecoration(MRecyclerViewDecoration())
        binding.idStoriesList.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1))
                {
                    recyclerView.isNestedScrollingEnabled = false
                }
            }
        })
        binding.idPostList.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1))
                {
                    recyclerView.isNestedScrollingEnabled = false
                }
            }
        })
        binding.idPostList.adapter = mAdapter
        binding.idSwipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({ // Stop animation (This will be after 3 seconds)
                binding.idSwipeRefreshLayout.isRefreshing = false
            }, 3000)
        }
        binding.lifecycleOwner = this
        return binding.root
    }
}
class MRecyclerViewDecoration:RecyclerView.ItemDecoration()
{
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.set(8,12,5,5)

    }
}