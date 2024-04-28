package com.example.touristsblog.di

import android.app.Application
import android.content.Context
import android.location.LocationManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.touristsblog.network.DadataRepository
import com.example.touristsblog.network.DadataRepositoryImpl
import com.example.touristsblog.network.myposts.PostRepositoryImpl
import com.example.touristsblog.network.myposts.PostsRepository
import com.example.touristsblog.network.user.UserRepository
import com.example.touristsblog.network.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun prefs(app: Application) = app.applicationContext.dataStore
}
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {
    @Binds
    fun signInRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
    @Binds
    fun postsRepository(postsRepository: PostRepositoryImpl): PostsRepository
    @Binds
    fun dadataRepository(dadataRepository: DadataRepositoryImpl): DadataRepository
}
