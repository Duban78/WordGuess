package com.example.wordguess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wordguess.ui.theme.WordGuessTheme
import com.example.wordguess.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppWordGuess()
        }
    }
}

@Composable
fun AppWordGuess() {
    val pantallaActual = remember { mutableStateOf(Pantalla.HOME) }

    when (pantallaActual.value) {
        Pantalla.HOME -> HomeScreen(pantallaActual)
        Pantalla.JUEGO -> Text("Pantalla de juego")
        Pantalla.ESTADISTICAS -> Text("Pantalla de estadísticas")
    }
}



enum class Pantalla {
    HOME,
    JUEGO,
    ESTADISTICAS
}

enum class Estado {
    CORRECTA,   // verde
    PRESENTE,   // amarillo
    AUSENTE,    // gris
    VACIA       // todavía sin letra
}