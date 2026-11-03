package com.example.wordguess.viewmodel

import com.example.wordguess.model.Estadisticas

class EstadisticasViewModel {

    // DATOS DE PRUEBA para el avance (iguales a los del mockup).
    // Más adelante se leerán de lo que guarde el juego.
    val estadisticas = Estadisticas(
        partidasJugadas = 42,
        victorias = 32,
        rachaActual = 5,
        mejorRacha = 11,
        distribucion = listOf(1, 3, 9, 11, 6, 2)
    )
}