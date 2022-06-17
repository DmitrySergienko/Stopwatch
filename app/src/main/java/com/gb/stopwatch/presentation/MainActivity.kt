package com.gb.stopwatch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.coroutineScope

import com.gb.stopwatch.data.StopwatchListOrchestrator
import com.gb.stopwatch.databinding.ActivityMainBinding
import com.gb.stopwatch.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest

import kotlinx.coroutines.launch

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }
    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    )
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycle.coroutineScope.launch {
            viewModel.ticker.collectLatest {
                binding.textTime.text = it
            }
        }

        viewModel.getTime()

//
        binding.buttonStart.setOnClickListener {
            stopwatchListOrchestrator.start()
        }
        binding.buttonPause.setOnClickListener {
            stopwatchListOrchestrator.pause()
        }
        binding.buttonStop.setOnClickListener {
            stopwatchListOrchestrator.stop()
        }



    }
}















