package com.gb.stopwatch.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.stopwatch.data.StopwatchListOrchestrator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

<<<<<<< HEAD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val DEFAULT_PREVIOUS_TIME = 0.00f


class MainActivityViewModel(
    private val stopwatchListOrchestrator: StopwatchListOrchestrator
) :
    ViewModel() {

    private val timeData: MutableLiveData<StateFlow<String>> by lazy { MutableLiveData<StateFlow<String>>() }

    fun getTime() = viewModelScope.launch {

        val time = stopwatchListOrchestrator.ticker
        timeData.postValue(time)
=======
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
>>>>>>> f0e15f9 (4.Try to get data using coroutines and viewModel)

    }

}

