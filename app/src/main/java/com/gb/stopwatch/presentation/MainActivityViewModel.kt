package com.gb.stopwatch.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.stopwatch.domain.StopwatchStateHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class MainActivityViewModel(
    private val stopwatchStateHolder: StopwatchStateHolder
) : ViewModel() {

    private val mutableTicker = MutableStateFlow("")
    val ticker: StateFlow<String> = mutableTicker


    fun getTime() = viewModelScope.launch {
        while (isActive) {
            mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
            delay(20)
        }


    }

}

