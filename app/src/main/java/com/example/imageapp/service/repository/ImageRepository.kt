package com.example.imageapp.service.repository

import com.example.imageapp.model.NetworkItemModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    /**
     * Get the list of images with author name
     */
    suspend fun getImageInfo(): Flow<List<NetworkItemModel?>>
}