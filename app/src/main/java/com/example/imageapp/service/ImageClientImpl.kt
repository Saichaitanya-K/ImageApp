package com.example.imageapp.service

import android.util.Log
import com.example.imageapp.model.NetworkItemModel

/**
 * Class to handle the responses
 */
class ImageClientImpl(
    private val imageAPi: ImageAPI
) : ImageClient {

    override suspend fun getImageInfo(): List<NetworkItemModel?> {
        return try {
            val call = imageAPi.getImageInfo()
            if (call.isSuccessful){
                call.body() ?: listOf()
            } else listOf()
        }catch (e: Exception){
            Log.e("Exception","Exception is caused by ${e.message}")
            listOf()
        }
    }
}