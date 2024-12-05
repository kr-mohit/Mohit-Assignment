package com.example.androidassignment.utils

import android.view.View

object Utils {

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }
}