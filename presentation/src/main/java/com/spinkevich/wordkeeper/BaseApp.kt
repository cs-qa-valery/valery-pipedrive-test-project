package com.spinkevich.wordkeeper

import android.app.Application
import com.spinkevich.data.interactor.TranslateInteractor
import com.spinkevich.data.mapper.ResponseToDomainMapper
import com.spinkevich.data.repository.TranslateRepositoryImpl
import com.spinkevich.data.source.api.service.TranslateService
import com.spinkevich.domain.repository.TranslateRepository
import com.spinkevich.domain.usecase.TranslateUseCase
import com.spinkevich.wordkeeper.core.TranslateViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.newInstance
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://dictionary.yandex.net/"

class BaseApp : Application(), KodeinAware {

    private val dataModule = Kodein.Module("data", false) {
        bind<ResponseToDomainMapper>() with singleton { ResponseToDomainMapper() }
        bind<TranslateService>() with singleton { retrofit.create(TranslateService::class.java) }
        bind<TranslateRepository>() with singleton {
            TranslateRepositoryImpl(
                instance(),
                instance()
            )
        }
        bind<TranslateViewModelFactory>() with singleton { TranslateViewModelFactory(instance()) }
    }

    private val domainModule = Kodein.Module("domain", false) {
        bind<TranslateUseCase>() with singleton { TranslateInteractor(instance()) }
    }

    override val kodein: Kodein = Kodein {
        import(dataModule)
        import(domainModule)
    }

    private val httpClient: OkHttpClient by kodein.newInstance {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit: Retrofit by kodein.newInstance {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}