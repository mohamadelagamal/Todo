package com.example.usercase.room

import com.example.usercase.repos.SourceOfflineRepository
import com.example.usercase.repos.SourcesOfflineDataSourceImpl
import com.route.todo_c35_sat.database.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {
    @Provides
    @Singleton
    // get repository
    fun provideOfflineDataSource(dataBase: MyDataBase): SourceOfflineRepository {
        return SourcesOfflineDataSourceImpl(dataBase)
    }
    @Singleton
    @Provides
    fun provideDatabase():MyDataBase{
        return MyDataBase.returnRoom()
    }
}