package com.example.wordguess.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordguess.Estado

// Color de fondo según el estado (lo usan Ficha, Tecla y la pantalla de estadísticas)
fun colorDeEstado(estado: Estado): Color = when (estado) {
    Estado.CORRECTA -> Color(0xFF6AAA64)
    Estado.PRESENTE -> Color(0xFFC9B458)
    Estado.AUSENTE -> Color(0xFF787C7E)
    Estado.VACIA -> Color.White
}

// Un cuadro de la cuadrícula 6x5
@Composable
fun Ficha(letra: Char, estado: Estado, modifier: Modifier = Modifier) {
    val vacia = estado == Estado.VACIA
    val forma = RoundedCornerShape(4.dp)

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(colorDeEstado(estado), forma)
            .border(2.dp, if (vacia) Color(0xFFD3D6DA) else colorDeEstado(estado), forma),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (letra == ' ') "" else letra.toString(),
            color = if (vacia) Color.Black else Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}