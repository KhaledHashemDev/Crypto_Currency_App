package com.example.crypto_currency_app.di

import com.example.crypto_currency_app.data.CoinPaprikaApi
import com.example.crypto_currency_app.data.repository.CoinRepositoryImpl
import com.example.crypto_currency_app.domain.repository.CoinRepository
import com.example.crypto_currency_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *The whole purpose of implementing dependency injection is that
 * it helps us to have our dependencies replaceable, in which testing would
 * be much more easier because no one specific dependency is hardcoded
 * We want to avoid hardcoding our dependencies into our objects
 *
 * installing it into our Singleton component means that all the dependencies in
 *module will live as long as th application is alive
 */

@Module // A Hilt module is a class that is annotated with @Module,unlike Dagger modules, you must annotate Hilt modules with
@InstallIn(SingletonComponent::class) //to tell Hilt which Android class each module will be used or installed in.
object AppModule {

    @Provides // function provides the dependency
    @Singleton // ensures we only have a single instance of whatever the function returns (not to be confused with singleton component
fun providesPaprikaApi() : CoinPaprikaApi {
    return Retrofit.Builder()   // now we are telling dagger.hilt how to create the API
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //please use GSON to serialize/deserialize Json data
        .build()
        .create(CoinPaprikaApi::class.java)
}

    @Provides
    @Singleton
    fun providesCoinRepository(api: CoinPaprikaApi) : CoinRepository {
        return CoinRepositoryImpl(api)
    }


}