package com.example.tamingtemper_androidchallenge.di

import android.app.Application
import com.example.tamingtemper_androidchallenge.data.datasource.Datasource
import com.example.tamingtemper_androidchallenge.data.repositories.UserRepositoryImpl
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
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
    ): Datasource = Datasource(application)

    @Provides
    @Singleton
    fun provideUserRepository(
        application: Application,
        datasource: Datasource
    ): UserRepository = UserRepositoryImpl(
        application,
        datasource,
    )
}