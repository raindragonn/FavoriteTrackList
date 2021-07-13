package com.raindragonn.favoritetracklist.di

import androidx.room.Room
import com.raindragonn.favoritetracklist.data.local.LocalDataSource
import com.raindragonn.favoritetracklist.data.local.LocalDataSourceImpl
import com.raindragonn.favoritetracklist.data.local.room.FavoriteDao
import com.raindragonn.favoritetracklist.data.local.room.FavoriteDataBase
import com.raindragonn.favoritetracklist.data.remote.RemoteDataSource
import com.raindragonn.favoritetracklist.data.remote.RemoteDataSourceImpl
import com.raindragonn.favoritetracklist.data.remote.api.ItunesApi
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.data.repository.TrackRepositoryImpl
import com.raindragonn.favoritetracklist.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModel
    viewModel<MainViewModel> { MainViewModel(get<TrackRepository>()) }

    // Repository
    single<TrackRepository> { TrackRepositoryImpl(get<RemoteDataSource>(), get<LocalDataSource>()) }

    // DataBase
    single<FavoriteDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            FavoriteDataBase::class.java,
            "favorite_db"
        ).build()
    }
    // Dao
    single<FavoriteDao> { get<FavoriteDataBase>().dao }

    // DataSource
    single<LocalDataSource> { LocalDataSourceImpl(get<FavoriteDao>()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get<ItunesApi>()) }
}