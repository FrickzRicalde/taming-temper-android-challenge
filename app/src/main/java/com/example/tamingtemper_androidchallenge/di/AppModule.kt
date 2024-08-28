package com.example.tamingtemper_androidchallenge.di

import android.app.Application
import com.example.tamingtemper_androidchallenge.data.datasource.LocalDatasource
import com.example.tamingtemper_androidchallenge.data.manager.LocalUserManagerImpl
import com.example.tamingtemper_androidchallenge.data.repositories.UserRepositoryImpl
import com.example.tamingtemper_androidchallenge.domain.manager.LocalUserManager
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
import com.example.tamingtemper_androidchallenge.domain.usecases.LoadTemperLevels
import com.example.tamingtemper_androidchallenge.domain.usecases.SaveTemperLevels
import com.example.tamingtemper_androidchallenge.domain.usecases.TemperLevelsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        application: Application
    ):LocalDatasource = LocalDatasource(application)

    @Provides
    @Singleton
    fun provideLocalUserManage(
        application: Application
    ):LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideUserRepository(
        application: Application,
        localDatasource: LocalDatasource
    ):UserRepository = UserRepositoryImpl(
        application,
        localDatasource,
    )

    @Provides
    @Singleton
    fun provideTemperLevelsUseCases(
        localUserManager: LocalUserManager
    ) = TemperLevelsUseCases(
        saveTemperLevels = SaveTemperLevels(localUserManager),
        loadTemperLevels = LoadTemperLevels(localUserManager),
    )
}