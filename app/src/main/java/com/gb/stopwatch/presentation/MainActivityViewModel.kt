package com.gb.stopwatch.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.stopwatch.data.StopwatchListOrchestrator
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

    }

}

