package com.example.dogimage.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dogimage.intent.DogIntent
import com.example.dogimage.state.DogState
import com.example.dogimage.viewmodel.DogViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DogScreen(viewModel: DogViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is DogState.Idle -> {
                Button(onClick = { viewModel.handleIntent(DogIntent.LoadRandomDogImage) }) {
                    Text("Start")
                }
            }
            is DogState.Loading -> {
                CircularProgressIndicator()
            }
            is DogState.DogImageLoaded -> {
                val imageUrl = (state as DogState.DogImageLoaded).imageUrl
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Random Dog",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Button(onClick = { viewModel.handleIntent(DogIntent.LoadRandomDogImage) }) {
                    Text("another one")
                }
            }
            is DogState.Error -> {
                val message = (state as DogState.Error).message
                Text("Error: $message", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
