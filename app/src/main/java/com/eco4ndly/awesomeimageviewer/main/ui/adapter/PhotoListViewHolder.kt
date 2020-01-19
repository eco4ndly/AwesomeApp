package com.eco4ndly.awesomeimageviewer.main.ui.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.eco4ndly.awesomeimageviewer.R
import com.eco4ndly.awesomeimageviewer.infra.GlideApp
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo
import kotlinx.android.synthetic.main.item_photo_list.view.*

/**
 * A Sayan Porya code on 2020-01-19
 */
class PhotoListViewHolder(
    itemView: View,
    onItemClick: (pos: Int) -> Unit
): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(adapterPosition)
            }
        }
    }
    fun bind(photo: Photo) {
        GlideApp.with(itemView.context)
            .load(photo.url)
            .error(R.drawable.ic_error_outline_black_24dp)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.progress_image_item.visibility = View.GONE

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.progress_image_item.visibility = View.GONE
                    itemView.iv_image_list.background = resource
                    return true
                }

            })
            .into(itemView.iv_image_list)
    }
}