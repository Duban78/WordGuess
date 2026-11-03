package com.example.wordguess.model

import com.example.wordguess.Estado


data class LetraEvaluada(
    val letra: Char,
    val estado: Estado
)