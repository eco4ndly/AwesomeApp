package com.eco4ndly.awesomeimageviewer.main.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.eco4ndly.awesomeimageviewer.R
import com.eco4ndly.awesomeimageviewer.base.BaseFragment
import com.eco4ndly.awesomeimageviewer.imagedetails.ImageDetailsFragment
import com.eco4ndly.awesomeimageviewer.main.MainActivityViewModel
import com.eco4ndly.awesomeimageviewer.main.ui.adapter.PhotoListAdapter
import kotlinx.android.synthetic.main.fragment_photo_list.*
import javax.inject.Inject

/**
 * A Sayan Porya code on 2020-01-19
 */
class PhotoListFragment : BaseFragment() {

    companion object {
        const val TAG = "PhotoListFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(MainActivityViewModel::class.java)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_photo_list
    }

    override fun takeOff(savedInstanceState: Bundle?) {
        val photoListAdapter: PhotoListAdapter = PhotoListAdapter {positionClicked ->
            mainViewModel.imageDetailsFragmentDataSet.currentImagePosition = positionClicked
            addOrReplaceFragment(ImageDetailsFragment(), ImageDetailsFragment.TAG)
        }

        photo_list.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = photoListAdapter
        }

        mainViewModel.photoListLiveData.observe(this, Observer {
            photoListAdapter.setData(it)
        })

        mainViewModel.showLoaderLiveData.observe(this, Observer {
            if (it) {
                photo_list_progress.visibility = View.VISIBLE
            } else {
                photo_list_progress.visibility = View.GONE
            }
        })

        mainViewModel.showErrorDialogLiveData.observe(this, Observer {
            AlertDialog.Builder(context).apply {
                setTitle("Error!")
                setMessage(it)
                setCancelable(false)
                setPositiveButton("Ok") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                create().show()
            }
        })

        mainViewModel.getPhotos()
    }
}