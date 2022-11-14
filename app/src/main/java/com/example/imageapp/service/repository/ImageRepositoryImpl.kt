package com.example.imageapp.service.repository

import com.example.imageapp.model.NetworkItemModel
import com.example.imageapp.service.ImageClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageRepositoryImpl(
    private val imageClient: ImageClient
): ImageRepository {

    override suspend fun getImageInfo(): Flow<List<NetworkItemModel?>> {
        return flow { emit(imageClient.getImageInfo()) }
    }
}