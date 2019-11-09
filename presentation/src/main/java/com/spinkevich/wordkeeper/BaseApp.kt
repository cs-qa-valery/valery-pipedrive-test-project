package com.spinkevich.wordkeeper

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.spinkevich.data.interactor.StudyWordsInteractor
import com.spinkevich.data.interactor.TranslateInteractor
import com.spinkevich.data.mapper.DatabaseToDomainMapper
import com.spinkevich.data.mapper.ResponseToDomainMapper
import com.spinkevich.data.repository.StudyWordsRepositoryImpl
import com.spinkevich.data.repository.TranslateRepositoryImpl
import com.spinkevich.data.source.api.service.TranslateService
import com.spinkevich.data.source.db.WordsDatabase
import com.spinkevich.data.source.db.dao.WordsDao
import com.spinkevich.domain.repository.StudyWordsRepository
import com.spinkevich.domain.repository.TranslateRepository
import com.spinkevich.domain.usecase.StudyWordsUseCase
import com.spinkevich.domain.usecase.TranslateUseCase
import com.spinkevich.wordkeeper.core.StudyViewModelFactory
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

private const val DATABASE_NAME = "wordkeeper.db"
private const val BASE_URL = "https://dictionary.yandex.net/"

class BaseApp : Application(), KodeinAware {

    private val dataModule = Kodein.Module("data", false) {
        bind<ResponseToDomainMapper>() with singleton { ResponseToDomainMapper() }
        bind<DatabaseToDomainMapper>() with singleton { DatabaseToDomainMapper() }
        bind<TranslateService>() with singleton { retrofit.create(TranslateService::class.java) }
        bind<TranslateRepository>() with singleton {
            TranslateRepositoryImpl(
                instance(),
                instance()
            )
        }
        bind<StudyWordsRepository>() with singleton {
            StudyWordsRepositoryImpl(
                instance(),
                instance()
            )
        }
        bind<WordsDao>() with singleton { database.wordsDao() }
    }

    private val domainModule = Kodein.Module("domain", false) {
        bind<TranslateUseCase>() with singleton { TranslateInteractor(instance()) }
        bind<StudyWordsUseCase>() with singleton { StudyWordsInteractor(instance()) }
    }

    private val presentationModule = Kodein.Module("presentation", false) {
        bind<TranslateViewModelFactory>() with singleton {
            TranslateViewModelFactory(
                instance(),
                instance()
            )
        }
        bind<StudyViewModelFactory>() with singleton { StudyViewModelFactory(instance()) }
    }

    override val kodein: Kodein = Kodein {
        import(dataModule)
        import(domainModule)
        import(presentationModule)
    }

    private val context: Context by kodein.newInstance { applicationContext }

    private val database: WordsDatabase by kodein.newInstance {
        Room.databaseBuilder(
            context,
            WordsDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
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