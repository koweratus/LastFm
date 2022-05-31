package com.example.songkickprojektanz.di

import android.app.Application
import androidx.room.Room
import com.example.songkickprojektanz.Constants
import com.example.songkickprojektanz.Constants.BASE_URL
import com.example.songkickprojektanz.data.local.LastFmDatabase
import com.example.songkickprojektanz.remote.LastFmApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(htttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(htttpLoggingInterceptor)
            .callTimeout(
                15, TimeUnit.SECONDS
            )
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesTmdbApi(okHttpClient: OkHttpClient): LastFmApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(LastFmApi::class.java)
    }

    @Provides
    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    fun provideLastFmDatabase(application: Application): LastFmDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            LastFmDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build() // The reason we can construct a database for the repo
    }

    @Singleton
    @Provides
    fun provideTmdbDao(db: LastFmDatabase) = db.getLastfmDao() // The reason we can implement a Dao for the database
}
