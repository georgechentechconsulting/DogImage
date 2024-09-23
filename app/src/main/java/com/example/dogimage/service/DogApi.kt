package com.example.dogimage.service

import com.example.dogimage.service.data.DogResponse
import retrofit2.http.GET

interface DogApi {
    @GET("api/breeds/image/random")
    suspend fun getRandomDogImage(): DogResponse
}