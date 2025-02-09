package com.samapp.getswipeinternshipassigment.di

import android.app.Application
import androidx.room.Room
import com.samapp.getswipeinternshipassigment.common.Constants
import com.samapp.getswipeinternshipassigment.data.local.database.AppDatabase
import com.samapp.getswipeinternshipassigment.data.remote.api.productApiService
import com.samapp.getswipeinternshipassigment.data.repositroy.AppRepositoryImpl
import com.samapp.getswipeinternshipassigment.domain.repository.AppRepository
import com.samapp.getswipeinternshipassigment.domain.use_case.AddProductUseCase
import com.samapp.getswipeinternshipassigment.domain.use_case.GetProductUseCase
import com.samapp.getswipeinternshipassigment.presentation.productAddition.productAdditionViewModel
import com.samapp.getswipeinternshipassigment.presentation.productList.ProductListViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val AppModule : Module = module {
    single {
        val client = OkHttpClient.Builder()
            .build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    single {
        get<Retrofit>().create(productApiService::class.java)
    }

    // Room Database setup
    single {
        Room.databaseBuilder(
            get<Application>().applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    // DAO
    single {
        get<AppDatabase>().productDao()
    }

    //Repository
    single<AppRepository> {
        AppRepositoryImpl(get(),get())
    }


    //Use Cases
    single {
        GetProductUseCase(get())
    }
    single {
        AddProductUseCase(get())
    }

    //View Models
    viewModel { ProductListViewModel(get()) }
    viewModel { productAdditionViewModel(get()) }

}