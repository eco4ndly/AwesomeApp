package com.eco4ndly.awesomeimageviewer.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eco4ndly.awesomeimageviewer.R
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo

/**
 * A Sayan Porya code on 2020-01-19
 */
class PhotoListAdapter(private val onItemClick: (pos: Int) -> Unit): RecyclerView.Adapter<PhotoListViewHolder>() {
    private val photoList: MutableList<Photo> = ArrayList()

    fun setData(newList: List<Photo>) {
        if (newList.isEmpty() || newList === photoList) return

        val diff = DiffUtil.calculateDiff(getDiffUtilCallback(photoList, newList))
        photoList.clear()
        photoList.addAll(newList)

        diff.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_photo_list, parent, false).run {
            return PhotoListViewHolder(this, onItemClick)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    /**
     * Allows the adapter implementation to provide a custom DiffUtil.Callback
     * If not, then the abstract implementation will be used
     */
    private fun getDiffUtilCallback(oldData: List<Photo>, newData: List<Photo>): DiffUtil.Callback {
        return object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                areItemsTheSame(oldData[oldItemPosition], newData[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                areContentsTheSame(oldData[oldItemPosition], newData[newItemPosition])

            override fun getOldListSize() = oldData.size

            override fun getNewListSize() = newData.size
        }
    }

    private fun areItemsTheSame(old: Photo, new: Photo): Boolean {
        return old == new
    }

    private fun areContentsTheSame(old: Photo, new: Photo): Boolean {
        return old == new
    }
}