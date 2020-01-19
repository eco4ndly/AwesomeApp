package com.eco4ndly.awesomeimageviewer.imagedetails

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.eco4ndly.awesomeimageviewer.R
import com.eco4ndly.awesomeimageviewer.base.BaseFragment
import com.eco4ndly.awesomeimageviewer.main.MainActivityViewModel
import com.eco4ndly.awesomeimageviewer.main.ui.adapter.DetailsPageViewPagetAdapter
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo
import kotlinx.android.synthetic.main.fragment_image_details.*
import javax.inject.Inject

/**
 * A Sayan Porya code on 2020-01-19
 */
class ImageDetailsFragment: BaseFragment() {

    companion object {
        const val TAG = "ImageDetailsFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var currentImagePosition = 0
    private val photoList: MutableList<Photo> = mutableListOf()

    private val mainViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_image_details
    }

    override fun takeOff(savedInstanceState: Bundle?) {
        mainViewModel.photoListLiveData.value?.let {
            currentImagePosition = mainViewModel.imageDetailsFragmentDataSet.currentImagePosition
            photoList.clear()
            photoList.addAll(it)
        }
        val viewPageAdapter = DetailsPageViewPagetAdapter(photoList)
        viewpager_details.adapter = viewPageAdapter
        viewpager_details.currentItem = currentImagePosition

        viewpager_details.addOnPageChangeListener(object :ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mainViewModel.imageDetailsFragmentDataSet.currentImagePosition = position
            }

        })

    }
}