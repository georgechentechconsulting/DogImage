package com.example.dogimage.intent

sealed class DogIntent {
    object LoadRandomDogImage : DogIntent()
}