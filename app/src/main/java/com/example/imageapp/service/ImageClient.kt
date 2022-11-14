package com.example.imageapp.service

import com.example.imageapp.model.NetworkItemModel

interface ImageClient {
    /**
     * Get the list of images with author name
     */
    suspend fun getImageInfo(): List<NetworkItemModel?>
}