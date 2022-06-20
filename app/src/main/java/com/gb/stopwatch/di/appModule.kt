package com.gb.stopwatch.di

import com.gb.stopwatch.presentation.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

   viewModel { MainActivityViewModel(get()) }


}