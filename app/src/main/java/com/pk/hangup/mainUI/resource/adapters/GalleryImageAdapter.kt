package com.pk.hangup.mainUI.resource.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pk.hangup.databinding.SingleImageViewBinding
import com.squareup.picasso.Picasso

class GalleryImageAdapter(private val list:MutableList<ImageData>):
    RecyclerView.Adapter<GalleryImageAdapter.ImageViewHolder>() {
    val items = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ImageViewHolder {
        return ImageViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ImageViewHolder private constructor(private val binding:SingleImageViewBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item:ImageData)
        {
            binding.imageData = item
            //Picasso.get().load(item.uri).resize(100,100).centerCrop().into(binding.imageView)
            binding.executePendingBindings()
        }
        companion object{
            fun getViewHolder(parent: ViewGroup):ImageViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = SingleImageViewBinding.inflate(layoutInflater,parent,false)
                return ImageViewHolder(view)
            }
        }
    }
}