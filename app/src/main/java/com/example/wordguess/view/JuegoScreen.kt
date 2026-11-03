package com.example.wordguess.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.IconButton
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
import com.example.wordguess.Estado
import com.example.wordguess.Pantalla
import com.example.wordguess.viewmodel.JuegoViewModel

@Composable
fun JuegoScreen(pantalla: MutableState<Pantalla>) {
    val viewModel = remember { JuegoViewModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()   // evita que quede debajo de la barra de estado
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Barra superior: intento actual y botón X para volver al inicio
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.size(48.dp))
            Text(
                text = "attempt ${viewModel.intentoActual}/${viewModel.maxIntentos}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            IconButton(onClick = { pantalla.value = Pantalla.HOME }) {
                Text("✕", fontSize = 20.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cuadrícula 6x5
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 280.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            for (fila in 0 until viewModel.maxIntentos) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    for (columna in 0 until viewModel.longitudPalabra) {
                        val celda = viewModel.letraEn(fila, columna)
                        Ficha(
                            letra = celda.letra,
                            estado = celda.estado,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Empuja el teclado hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // Teclado QWERTY (por ahora las teclas no hacen nada)
        for ((indice, fila) in viewModel.filasTeclado.withIndex()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (indice == 1) Spacer(modifier = Modifier.weight(0.5f))
                if (indice == 2) {
                    Tecla("ENTER", Estado.VACIA, onClick = { }, modifier = Modifier.weight(1.5f))
                }
                for (letra in fila) {
                    Tecla(
                        texto = letra.toString(),
                        estado = viewModel.estadoTecla(letra),
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (indice == 2) {
                    Tecla("←", Estado.VACIA, onClick = { }, modifier = Modifier.weight(1.5f))
                }
                if (indice == 1) Spacer(modifier = Modifier.weight(0.5f))
            }
        }
    }
}