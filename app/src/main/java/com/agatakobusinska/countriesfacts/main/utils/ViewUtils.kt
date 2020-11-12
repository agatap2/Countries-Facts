package com.agatakobusinska.countriesfacts.main.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar


class ViewUtils {

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    fun showSnackbar(layout: ViewGroup, text: String) {
        Snackbar.make(layout, text, Snackbar.LENGTH_SHORT)
    }
}

fun ImageView.loadImage(url: String?, context: Context) {
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()

    val request = ImageRequest.Builder(this.context)
        .data(url)
        .target(this)
        .build()
    imageLoader.enqueue(request)
}

