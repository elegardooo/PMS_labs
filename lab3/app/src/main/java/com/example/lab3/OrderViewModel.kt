package com.example.lab3

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    var selectedEntree = mutableStateOf<MenuItem?>(null)
    var selectedSideDish = mutableStateOf<MenuItem?>(null)
    var selectedAccompaniment = mutableStateOf<MenuItem?>(null)

    fun calculateTotalPrice(): Double {
        return listOfNotNull(selectedEntree.value, selectedSideDish.value, selectedAccompaniment.value)
            .sumOf { it.price }
    }
}