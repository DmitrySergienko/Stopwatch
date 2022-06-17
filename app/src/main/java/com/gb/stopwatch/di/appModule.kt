package com.gb.stopwatch.di

import com.gb.stopwatch.data.StopwatchListOrchestrator
import com.gb.stopwatch.domain.*
import com.gb.stopwatch.presentation.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {


    viewModel { MainActivityViewModel(stopwatchListOrchestrator = get()) }

    single {
        val timestampProvider = object : TimestampProvider {
            override fun getMilliseconds(): Long {
                return System.currentTimeMillis()
            }
        }

        StopwatchListOrchestrator(
            StopwatchStateHolder(
                StopwatchStateCalculator(timestampProvider,
                    ElapsedTimeCalculator(timestampProvider)
                ),
                ElapsedTimeCalculator(timestampProvider), TimestampMillisecondsFormatter()
            ),
            CoroutineScope(Dispatchers.IO + SupervisorJob()),

        )
    }
}