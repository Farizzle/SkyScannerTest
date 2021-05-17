package com.example.skyscanner.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.skyscanner.R
import com.example.skyscanner.data.local.FlightsDAO
import com.example.skyscanner.data.local.FlightsDatabase
import com.example.skyscanner.data.remote.SkyScannerAPI
import com.example.skyscanner.repositories.DefaultFlightsRepository
import com.example.skyscanner.repositories.FlightRepository
import com.example.skyscanner.util.Constants.BASE_URL
import com.example.skyscanner.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            FlightsDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideFlightsDao(database: FlightsDatabase) =
        database.flightDao()

    @Singleton
    @Provides
    fun getSkyScannerAPI() : SkyScannerAPI {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()
            .create(SkyScannerAPI::class.java)
    }

    @Singleton
    @Provides
    fun getDefaultRepository(dao: FlightsDAO, skyScannerAPI: SkyScannerAPI) =
        DefaultFlightsRepository(dao, skyScannerAPI) as FlightRepository

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext app: Context) =
        Glide.with(app)
            .setDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.ic_placeholder)
            )
}