package com.example.imageapp.service

import com.example.imageapp.model.NetworkItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageAPI {

    /**
     * Makes the get request
     */
    @GET("v2/list")
    suspend fun getImageInfo(
        @Query("page") pageNumber: Int? = 2,
        @Query("limit") pageLimit: Int? = 20
    ): Response<List<NetworkItemModel?>>
}