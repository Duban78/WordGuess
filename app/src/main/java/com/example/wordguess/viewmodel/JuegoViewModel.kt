package com.example.wordguess.viewmodel

import com.example.wordguess.Estado
import com.example.wordguess.model.LetraEvaluada

class JuegoViewModel {

    val maxIntentos = 6
    val longitudPalabra = 5

    // Filas del teclado QWERTY
    val filasTeclado = listOf("QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM")

    // DATOS DE PRUEBA para el avance (palabra secreta de ejemplo: SHAPE).
    // Más adelante se reemplazan por la lógica real del juego.
    val intentos: List<List<LetraEvaluada>> = listOf(
        listOf(
            LetraEvaluada('C', Estado.AUSENTE),
            LetraEvaluada('R', Estado.AUSENTE),
            LetraEvaluada('A', Estado.CORRECTA),
            LetraEvaluada('N', Estado.AUSENTE),
            LetraEvaluada('E', Estado.CORRECTA)
        ),
        listOf(
            LetraEvaluada('C', Estado.AUSENTE),
            LetraEvaluada('H', Estado.CORRECTA),
            LetraEvaluada('A', Estado.CORRECTA),
            LetraEvaluada('S', Estado.PRESENTE),
            LetraEvaluada('E', Estado.CORRECTA)
        )
    )

    // Número del intento que se está jugando (3 si ya hay 2 hechos)
    val intentoActual: Int
        get() = intentos.size + 1

    // Letra y color de una celda del tablero (fila y columna empiezan en 0)
    fun letraEn(fila: Int, columna: Int): LetraEvaluada =
        intentos.getOrNull(fila)?.getOrNull(columna)
            ?: LetraEvaluada(' ', Estado.VACIA)

    // Color de una tecla según lo que ya se probó: verde > amarillo > gris
    fun estadoTecla(letra: Char): Estado {
        val estados = intentos.flatten()
            .filter { it.letra == letra }
            .map { it.estado }
        return when {
            Estado.CORRECTA in estados -> Estado.CORRECTA
            Estado.PRESENTE in estados -> Estado.PRESENTE
            Estado.AUSENTE in estados -> Estado.AUSENTE
            else -> Estado.VACIA
        }
    }
}