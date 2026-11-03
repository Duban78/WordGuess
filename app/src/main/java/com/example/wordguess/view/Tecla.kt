package com.example.wordguess.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
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

// Una tecla del teclado en pantalla
@Composable
fun Tecla(
    texto: String,
    estado: Estado,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sinUsar = estado == Estado.VACIA

    Box(
        modifier = modifier
            .height(48.dp)
            .background(
                if (sinUsar) Color(0xFFD3D6DA) else colorDeEstado(estado),
                RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = if (sinUsar) Color.Black else Color.White,
            fontSize = if (texto.length > 1) 11.sp else 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}