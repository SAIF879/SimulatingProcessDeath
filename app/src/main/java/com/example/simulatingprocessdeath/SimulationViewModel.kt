package com.example.simulatingprocessdeath

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SimulationViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY = "text_input"
    }

    // Backing state
    private val _text = MutableStateFlow(state[KEY] ?: "")
    val text: StateFlow<String> = _text

    fun updateText(newValue: String) {
        _text.value = newValue
        state[KEY] = newValue
    }
}

