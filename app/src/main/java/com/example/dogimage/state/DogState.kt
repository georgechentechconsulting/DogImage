package com.example.dogimage.state


sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    data class DogImageLoaded(val imageUrl: String) : ViewState()
    data class DogError(val message: String) : ViewState()
    data class CatImageLoaded(val imageUrl: String) : ViewState()
    data class CatError(val imageUrl: String) : ViewState()

}