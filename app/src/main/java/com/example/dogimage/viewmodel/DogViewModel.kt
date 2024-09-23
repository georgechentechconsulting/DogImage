package com.example.dogimage.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogimage.intent.DogIntent
import com.example.dogimage.service.DogApi
import com.example.dogimage.state.DogState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class DogViewModel : ViewModel() {

    private val _state = MutableStateFlow<DogState>(DogState.Idle)
    val state: StateFlow<DogState> = _state

    private val dogApi: DogApi = Retrofit.Builder()
        .baseUrl("https://dog.ceo/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApi::class.java)

    fun handleIntent(intent: DogIntent) {
        when (intent) {
            is DogIntent.LoadRandomDogImage -> loadRandomDogImage()
        }
    }

    private fun loadRandomDogImage() {
        viewModelScope.launch {
            _state.value = DogState.Loading
            try {
                val response = dogApi.getRandomDogImage()
                _state.value = DogState.DogImageLoaded(response.message)
            } catch (e: Exception) {
                _state.value = DogState.Error("Failed to load image")
            }
        }
    }
}

