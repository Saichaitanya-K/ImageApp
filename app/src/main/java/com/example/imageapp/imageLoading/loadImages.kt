package com.example.imageapp.imageLoading

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

fun loadImages(url: Any, app: Application): Bitmap? {
    var bitmap: Bitmap? = null
     try {
         if (url is String || url is GlideUrl || url is Uri) {
             bitmap = Glide
                 .with(app)
                 .asBitmap()
                 .override(500)
                 .load(url)
                 .submit().get()
         }
    } catch (e: Exception){
        Log.e("Glide", "Image Loading Failed ${e.message}")
    }
    return bitmap
}