package com.example.imageapp.extensions

import com.example.imageapp.model.ItemModel
import com.example.imageapp.model.NetworkItemModel

/**
 * Extension method to convert network model to UI Model
 */
fun List<NetworkItemModel?>.toItemModel() =
    this.map {
        it?.let {
            ItemModel(
                id = it.id,
                title = it.title,
                imageWidth = it.imageWidth,
                imageHeight = it.imageHeight,
                description = "Author: ${it.title} \n Image Width(px): ${it.imageWidth} \n Image Height(px): ${it.imageHeight}",
                imageUrl = it.imageUrl
            )
        }
    }