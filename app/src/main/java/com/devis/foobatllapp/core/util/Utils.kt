package com.devis.foobatllapp.core.util

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream

/**
 * Created by Devis on 20/09/20
 */

fun ImageView.setImage(
    imageDrawable: Int? = null,
    imageUrl: String? = null
) {
    Glide.with(context)
        .load(imageDrawable ?: imageUrl)
        .into(this)
}

fun Drawable.convertToByteArray(): ByteArray {
    val bitmap = (this as BitmapDrawable).bitmap
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

    return stream.toByteArray()
}