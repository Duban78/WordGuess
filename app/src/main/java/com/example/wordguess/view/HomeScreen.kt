package com.example.wordguess.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordguess.Pantalla
import com.example.wordguess.viewmodel.HomeViewModel

@Composable
fun HomeScreen(pantalla: MutableState<Pantalla>) {
    val viewModel = remember { HomeViewModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Abc", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A4D9C))
        Spacer(modifier = Modifier.height(16.dp))
        Text(viewModel.nombreJuego, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(viewModel.descripcion, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { pantalla.value = Pantalla.JUEGO },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("play")
        }

        OutlinedButton(
            onClick = { pantalla.value = Pantalla.ESTADISTICAS },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("statistics")
        }

        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("difficulty / language")
        }
    }
}