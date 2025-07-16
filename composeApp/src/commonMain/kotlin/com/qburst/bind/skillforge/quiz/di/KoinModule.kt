package com.qburst.bind.skillforge.quiz.di

import com.qburst.bind.skillforge.di.platformModule
import com.qburst.bind.skillforge.quiz.data.mapper.AuthLoginMapper
import com.qburst.bind.skillforge.quiz.data.mapper.Mapper
import com.qburst.bind.skillforge.quiz.data.networking.ApiClientHelper
import com.qburst.bind.skillforge.quiz.data.repo.auth.AuthRepoImpl
import com.qburst.bind.skillforge.quiz.data.repo.auth.entity.UserLoginResponse
import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.domain.repo.AuthRepo
import com.qburst.bind.skillforge.quiz.domain.usecase.login.LoginUseCase
import com.qburst.bind.skillforge.quiz.domain.usecase.login.LoginUseCaseImpl
import com.qburst.bind.skillforge.quiz.presentation.ui.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val baseModule = module {

}

val networkModule = module {
    single { ApiClientHelper() }
}

val dataModule = module {
    single<AuthRepo> {
        AuthRepoImpl(
            apiClientHelper = get(),
            mapper = get()
        )
    }
}

val useCaseModule = module {
    single<LoginUseCase> { LoginUseCaseImpl(repo = get()) }
}

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
}

val mapperModule = module {
    single<Mapper<LoginData, UserLoginResponse>> { AuthLoginMapper() }
}

fun appModule() = listOf(
    baseModule,
    platformModule,
    networkModule,
    dataModule,
    useCaseModule,
    viewModelModule,
    mapperModule
)