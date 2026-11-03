package com.example.wordguess.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordguess.Estado
import com.example.wordguess.Pantalla
import com.example.wordguess.viewmodel.EstadisticasViewModel

@Composable
fun EstadisticasScreen(pantalla: MutableState<Pantalla>) {
    val viewModel = remember { EstadisticasViewModel() }
    val datos = viewModel.estadisticas
    val maximo = datos.distribucion.maxOrNull() ?: 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()   // evita que quede debajo de la barra de estado
            .padding(24.dp)
    ) {
        Text(
            text = "statistics",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Cuatro cuadros: partidas, porcentaje de victorias y rachas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CuadroEstadistica("${datos.partidasJugadas}", "games played", Modifier.weight(1f))
            CuadroEstadistica("${datos.porcentajeVictorias}%", "win rate", Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CuadroEstadistica("${datos.rachaActual}", "current streak", Modifier.weight(1f))
            CuadroEstadistica("${datos.mejorRacha}", "best streak", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Distribución de intentos con barras
        Text("guess distribution", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        for ((indice, cantidad) in datos.distribucion.withIndex()) {
            FilaDistribucion(numero = indice + 1, cantidad = cantidad, maximo = maximo)
        }

        // Empuja el botón hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { pantalla.value = Pantalla.HOME },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("back")
        }
    }
}

// Una fila de la distribución: número de intento, barra y cantidad
@Composable
private fun FilaDistribucion(numero: Int, cantidad: Int, maximo: Int) {
    // La barra más larga es la del máximo; las demás son proporcionales (mínimo 5% para que se vean)
    val fraccion = if (maximo == 0) 0.05f else (cantidad.toFloat() / maximo).coerceAtLeast(0.05f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$numero", fontSize = 14.sp, color = Color.Black, modifier = Modifier.width(24.dp))
        Box(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraccion)
                    .height(20.dp)
                    .background(colorDeEstado(Estado.CORRECTA), RoundedCornerShape(4.dp))
            )
        }
        Text(
            text = "$cantidad",
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.End,
            modifier = Modifier.width(32.dp)
        )
    }
}