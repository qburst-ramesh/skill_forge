package com.qburst.bind.skillforge.di

import com.qburst.bind.skillforge.MainViewController
import com.qburst.bind.skillforge.common.GoogleAuthManager
import com.qburst.bind.skillforge.common.IGoogleAuthManager
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<IGoogleAuthManager> { GoogleAuthManager(viewController = MainViewController()) }
}
