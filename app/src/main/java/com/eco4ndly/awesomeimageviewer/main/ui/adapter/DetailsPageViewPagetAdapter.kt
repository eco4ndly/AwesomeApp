package com.eco4ndly.awesomeimageviewer.main.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.eco4ndly.awesomeimageviewer.R
import com.eco4ndly.awesomeimageviewer.infra.GlideApp
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo
import kotlinx.android.synthetic.main.item_photo_detail.view.*

/**
 * A Sayan Porya code on 2020-01-19
 */
class DetailsPageViewPagetAdapter constructor(private val list: List<Photo>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    var prevDetailsVisibility = View.GONE

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_photo_detail, container, false)

        val photo = list[position]

        view?.run {
            tv_image_title.text = photo.title
            tv_image_desc.text = photo.explanation
            ll_details_container.visibility = prevDetailsVisibility
            iv_image_full.setOnClickListener {
                if (view.ll_details_container.visibility == View.VISIBLE) {
                    view.ll_details_container.visibility = View.GONE
                } else {
                    view.ll_details_container.visibility = View.VISIBLE
                }
                prevDetailsVisibility = view.ll_details_container.visibility
            }

            loadImageWithGlide(this, photo.url)
        }

        container.addView(view)
        return view
    }

    private fun loadImageWithGlide(view: View, url: String) {
        view.run {
            GlideApp.with(context)
                .load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progress_image_details.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progress_image_details.visibility = View.GONE
                        //iv_image_full.background = resource
                        iv_image_full.setImageDrawable(resource)
                        return true
                    }

                })
                .into(iv_image_full)
        }
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}