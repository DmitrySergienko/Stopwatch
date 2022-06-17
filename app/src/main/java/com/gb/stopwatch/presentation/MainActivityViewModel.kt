package com.gb.stopwatch.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.stopwatch.data.StopwatchListOrchestrator
import com.gb.stopwatch.domain.StopwatchStateHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


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

