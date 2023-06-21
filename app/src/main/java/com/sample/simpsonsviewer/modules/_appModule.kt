package com.sample.simpsonsviewer.modules

import com.sample.simpsonsviewer.api.DuckDuckGoService
import com.sample.simpsonsviewer.respository.DuckDuckGoRepository
import com.sample.simpsonsviewer.respository.mapper.ResponseMapper
import com.sample.simpsonsviewer.utility.Constants
import com.sample.simpsonsviewer.viewModels.DuckDuckGoViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { getDuckDuckGoService() }
    single { ResponseMapper() }
    single { DuckDuckGoRepository(get(), get()) }
    viewModel { DuckDuckGoViewModel(get()) }
}

private fun getDuckDuckGoService(): DuckDuckGoService {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
        .create(DuckDuckGoService::class.java)
}