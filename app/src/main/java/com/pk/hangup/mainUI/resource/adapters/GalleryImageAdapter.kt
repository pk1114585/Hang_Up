package com.pk.hangup.mainUI.resource.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pk.hangup.databinding.SingleImageViewBinding

class GalleryImageAdapter(private val list:MutableList<ImageData>,private val selectClickListener: GalleryImageSelectClickListener):
    RecyclerView.Adapter<GalleryImageAdapter.ImageViewHolder>() {
    val items = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ImageViewHolder {
        return ImageViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position],selectClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ImageViewHolder private constructor(private val binding:SingleImageViewBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item:ImageData,selectClickListener: GalleryImageSelectClickListener)
        {
            binding.imageData = item
            binding.selectListener = selectClickListener
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
class  GalleryImageSelectClickListener(private val selectClickListener:(uri: String)->Unit)
{
    fun onSelect(uri:String)
    {
        return selectClickListener(uri)
    }
}