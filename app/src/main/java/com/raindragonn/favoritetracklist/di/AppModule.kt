package com.raindragonn.favoritetracklist.di

import com.raindragonn.favoritetracklist.data.remote.api.ItunesApi
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.data.repository.TrackRepositoryImpl
import com.raindragonn.favoritetracklist.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> { MainViewModel(get<TrackRepository>()) }

    single<TrackRepository> { TrackRepositoryImpl(get<ItunesApi>()) }
}