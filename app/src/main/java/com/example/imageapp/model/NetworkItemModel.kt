package com.example.imageapp.model

import com.google.gson.annotations.SerializedName

/**
 * Data from server is mapped to this model
 */
data class NetworkItemModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("author")
    val title: String?,
    @SerializedName("width")
    val imageWidth: Long?,
    @SerializedName("height")
    val imageHeight: Long?,
    @SerializedName("download_url")
    val imageUrl: String? = null,
)
