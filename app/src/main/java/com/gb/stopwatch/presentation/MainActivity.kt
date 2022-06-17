package com.gb.stopwatch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.gb.stopwatch.data.StopwatchListOrchestrator
import com.gb.stopwatch.databinding.ActivityMainBinding
import com.gb.stopwatch.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    //to catch exception use coroutineExceptionHandler in first coroutine after scope or in the scope
    //SupervisorJob() protect scope dropdown
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

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


        //take out data from flow
        //scope.launch { stafeflow.collect{

       // } }

        viewModel.getTime().observe(this) {
            binding.textTime.text = it
        }

        binding.buttonStart.setOnClickListener {
            stopwatchListOrchestrator.start()
        }
        binding.buttonPause.setOnClickListener {
            stopwatchListOrchestrator.pause()
        }
        binding.buttonStop.setOnClickListener {
            stopwatchListOrchestrator.stop()
        }

        //      CoroutineScope(
        //          Dispatchers.Main + SupervisorJob()
        //      ).launch { stopwatchListOrchestrator.ticker.collect { binding.textTime.text = it } }
//
        //      binding.buttonStart.setOnClickListener {
        //          stopwatchListOrchestrator.start()
        //      }
        //      binding.buttonPause.setOnClickListener {
        //          stopwatchListOrchestrator.pause()
        //      }
        //      binding.buttonStop.setOnClickListener {
        //          stopwatchListOrchestrator.stop()
        //      }

    }
}















