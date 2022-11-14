package com.example.imageapp.network

import com.example.imageapp.service.ImageAPI
import com.example.imageapp.service.ImageClientImpl
import com.example.imageapp.service.repository.ImageRepository
import com.example.imageapp.service.repository.ImageRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {

    private var imageRepository: ImageRepository

    init {
        val retrofit = createRetrofit()
        imageRepository = ImageRepositoryImpl(
            ImageClientImpl(
                retrofit.create(ImageAPI::class.java)
            )
        )
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://picsum.photos/")
            .build()
    }

    fun getImageRepository() = imageRepository

    companion object{
        private var networkManager: NetworkManager? = null
        fun getInstance(): NetworkManager{
            return networkManager ?: NetworkManager()
        }
    }
}