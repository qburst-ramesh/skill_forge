package com.qburst.bind.skillforge.di

import com.qburst.bind.skillforge.common.GoogleAuthProvider
import com.qburst.bind.skillforge.common.createDataStore
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            // Optional client-wide config like timeouts
        }
    }

    single(named("refresh")) {
        HttpClient(OkHttp) { }
    }

    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class

    single { createDataStore(context = androidContext()) }

}
