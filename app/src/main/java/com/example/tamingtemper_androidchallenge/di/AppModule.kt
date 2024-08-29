package com.example.tamingtemper_androidchallenge.di

import android.app.Application
import com.example.tamingtemper_androidchallenge.data.datasource.ImageDatasourceImpl
import com.example.tamingtemper_androidchallenge.data.datasource.LocalDatasourceImpl
import com.example.tamingtemper_androidchallenge.data.datasource.RemoteDatasourceImpl
import com.example.tamingtemper_androidchallenge.data.repositories.UserRepositoryImpl
import com.example.tamingtemper_androidchallenge.domain.datasource.ImageDatasource
import com.example.tamingtemper_androidchallenge.domain.datasource.LocalDatasource
import com.example.tamingtemper_androidchallenge.domain.datasource.RemoteDatasource
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
    fun provideImageDataSource(
        application: Application
    ): ImageDatasource = ImageDatasourceImpl(application)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        application: Application
    ): LocalDatasource = LocalDatasourceImpl(application)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        application: Application
    ): RemoteDatasource = RemoteDatasourceImpl(application)

    @Provides
    @Singleton
    fun provideUserRepository(
        application: Application,
        imageDatasource: ImageDatasource,
        localDatasource: LocalDatasource,
        remoteDatasource: RemoteDatasource,
    ): UserRepository = UserRepositoryImpl(
        context = application,
        imageDatasource = imageDatasource,
        localDatasource = localDatasource,
        remoteDatasource = remoteDatasource,
    )
}