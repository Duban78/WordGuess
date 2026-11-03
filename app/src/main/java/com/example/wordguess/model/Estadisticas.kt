package com.example.wordguess.model

data class Estadisticas(
    val partidasJugadas: Int,
    val victorias: Int,
    val rachaActual: Int,
    val mejorRacha: Int,
    // victorias según el intento: posición 0 = intento 1, ..., posición 5 = intento 6
    val distribucion: List<Int>
) {
    val porcentajeVictorias: Int
        get() = if (partidasJugadas == 0) 0 else victorias * 100 / partidasJugadas
}