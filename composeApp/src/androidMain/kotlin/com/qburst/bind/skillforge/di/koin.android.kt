package com.qburst.bind.skillforge.di

import com.qburst.bind.skillforge.common.GoogleAuthProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}