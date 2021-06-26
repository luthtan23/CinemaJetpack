package com.luthtan.cinemajetpack.listener

import android.view.View

class CustomOnItemClickListener(private val onItemClickCallback: OnItemClickCallback) :
    View.OnClickListener {

    override fun onClick(view: View) {
        onItemClickCallback.onItemClicked(view)
    }

    interface OnItemClickCallback {
        fun onItemClicked(view: View)
    }
}