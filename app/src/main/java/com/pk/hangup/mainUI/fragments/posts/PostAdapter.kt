package com.pk.hangup.mainUI.fragments.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pk.hangup.databinding.PostViewBinding

class PostAdapter(private val list: MutableList<Users>,private val clickListener: PostClickListener) :
    RecyclerView.Adapter<PostAdapter.PostItemViewHolder>() {
    var postItems = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder
    {
        return PostItemViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int)
    {
        holder.bind(postItems[position],clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class PostItemViewHolder private constructor(private val binding: PostViewBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Users,clickListener: PostClickListener)
        {
            binding.users = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object
        {
            fun getViewHolder(parent: ViewGroup): PostItemViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = PostViewBinding.inflate(layoutInflater,parent,false)
                return PostItemViewHolder(view)
            }
        }
    }
}
class PostClickListener(private val clickListener:(itemId:String)->Unit)
{
    fun onClick(postItem:Users)
    {
        return clickListener(postItem.userName)
    }
}