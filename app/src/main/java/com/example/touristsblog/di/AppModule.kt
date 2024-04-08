package com.example.touristsblog.di

import android.app.Application
import com.example.touristsblog.localdb.AppDatabase
import com.example.touristsblog.network.UserRepository
import com.example.touristsblog.network.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun atmostateDatabase(app: Application) = AppDatabase.getInstance(app)

    /*@Provides
    @Singleton
    fun cityDao(db: AtmostateDatabase) = db.cityDatabaseDao

    @Provides
    @Singleton
    fun weatherDao(db: AtmostateDatabase) = db.weatherDatabaseDao

    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun weatherApi(moshi: Moshi) = WeatherApi(moshi)

    @Provides
    @Singleton
    fun prefs(app: Application) = app.applicationContext.dataStore

    @Provides
    @Singleton
    fun weatherRepo(
        weatherDao: WeatherDatabaseDao,
        cityDao: CityDatabaseDao,
        api: WeatherApi,
        lm: LocationManager,
        prefs: DataStore<Preferences>
    ) =
        WeatherRepository(weatherDao, cityDao, api, lm, prefs)

    @Provides
    @Singleton
    fun locationProvider(app: Application): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(app)


    @Provides
    @Singleton
    fun locationManager(
        fusedLocationClient: FusedLocationProviderClient,
    ) = LocationManager(fusedLocationClient)*/
}
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {
    @Binds
    fun signInRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
