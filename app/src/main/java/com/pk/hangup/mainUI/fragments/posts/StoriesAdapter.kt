package com.pk.hangup.mainUI.fragments.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pk.hangup.R

class StoriesAdapter: RecyclerView.Adapter<StoriesAdapter.MViewHolder>() {
    private var data = listOf<String>("Hello","new","Yes","Not sure","Iet agra")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.story_view,parent,false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val list = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class MViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {

    }
}