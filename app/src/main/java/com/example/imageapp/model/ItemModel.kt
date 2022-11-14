package com.example.imageapp.model

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.annotations.SerializedName

/**
 * UI Model
 */
data class ItemModel(
    val id: Int,
    val title: String?,
    val imageWidth: Long?,
    val imageHeight: Long?,
    var description: String? = null,
    val imageUrl: String? = null,
    var bitmap: MutableState<Bitmap?> = mutableStateOf(null)
)
