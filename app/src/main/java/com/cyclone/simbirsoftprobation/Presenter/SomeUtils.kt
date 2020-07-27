package com.cyclone.simbirsoftprobation.Presenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide

fun ImageView.loadBitmap(
    context: Context,
    bitmap: Bitmap?,
    placeholderDrawable: Int? = null
) {
    if (placeholderDrawable != null)
        Glide.with(context)
            .load(bitmap)
            .placeholder(resources.getDrawable(placeholderDrawable, context.theme))
            .into(this)
    else
        Glide.with(context)
            .load(bitmap)
            .into(this)
}

fun ImageView.loadDrawable(
    context: Context,
    image: Int?,
    placeholderDrawable: Int? = null
) {
    if (placeholderDrawable != null)
        Glide.with(context)
            .load(image)
            .placeholder(resources.getDrawable(placeholderDrawable, context.theme))
            .into(this)
    else
        Glide.with(context)
            .load(image)
            .into(this)
}