package com.qburst.bind.skillforge.quiz.di

import com.qburst.bind.skillforge.di.platformModule
import com.qburst.bind.skillforge.quiz.data.localStorage.PreferenceManager
import com.qburst.bind.skillforge.quiz.data.localStorage.PreferenceManagerImpl
import com.qburst.bind.skillforge.quiz.data.mapper.AuthLoginMapper
import com.qburst.bind.skillforge.quiz.data.mapper.HomeMapper
import com.qburst.bind.skillforge.quiz.data.mapper.Mapper
import com.qburst.bind.skillforge.quiz.data.networking.ApiClientHelper
import com.qburst.bind.skillforge.quiz.data.repo.auth.AuthRepoImpl
import com.qburst.bind.skillforge.quiz.data.repo.auth.entity.UserLoginResponse
import com.qburst.bind.skillforge.quiz.data.repo.home.HomeRepoImpl
import com.qburst.bind.skillforge.quiz.data.repo.home.TokenProviderImpl
import com.qburst.bind.skillforge.quiz.data.repo.home.entity.HomeResponseData
import com.qburst.bind.skillforge.quiz.domain.model.HomeData
import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.domain.repo.AuthRepo
import com.qburst.bind.skillforge.quiz.domain.repo.HomeRepo
import com.qburst.bind.skillforge.quiz.domain.repo.TokenProvider
import com.qburst.bind.skillforge.quiz.domain.usecase.home.HomeUseCase
import com.qburst.bind.skillforge.quiz.domain.usecase.home.HomeUseCaseImpl
import com.qburst.bind.skillforge.quiz.domain.usecase.login.LoginUseCase
import com.qburst.bind.skillforge.quiz.domain.usecase.login.LoginUseCaseImpl
import com.qburst.bind.skillforge.quiz.presentation.ui.landing.home.HomeViewModel
import com.qburst.bind.skillforge.quiz.presentation.ui.login.LoginViewModel
import com.qburst.bind.skillforge.quiz.presentation.ui.splash.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module


private const val HOME_MAPPER_NAME = "HomeMapper"
private const val AUTH_MAPPER_NAME = "AuthMapper"

val baseModule = module {

}

val networkModule = module {

    /* single(named("refresh")) {
         HttpClient(get<HttpClientEngine>())
     }*/


    single {
        ApiClientHelper(
            tokenProvider = get()
        )
    }

    single<TokenProvider> {
        TokenProviderImpl(
            preferenceManager = get(),
            refreshClient = get(named("refresh"))
        )
    }
}

val dataModule = module {
    single<AuthRepo> {
        AuthRepoImpl(
            apiClientHelper = get(),
            authMapper = get(named(AUTH_MAPPER_NAME)),
            tokenProvider = get()
        )
    }

    single<PreferenceManager> {
        PreferenceManagerImpl(dataStore = get())
    }

    single<HomeRepo> {
        HomeRepoImpl(
            apiClientHelper = get(),
            tokenProvider = get(),
            mapper = get(named(HOME_MAPPER_NAME))
        )
    }
}

val useCaseModule = module {
    single<LoginUseCase> {
        LoginUseCaseImpl(
            repo = get(), preferenceManager = get()
        )
    }
    single<HomeUseCase> {
        HomeUseCaseImpl(
            homeRepo = get()
        )
    }
}

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::HomeViewModel)
}

val mapperModule = module {
    single<Mapper<HomeData, List<HomeResponseData?>>>(named(HOME_MAPPER_NAME)) { HomeMapper() }
    single<Mapper<LoginData, UserLoginResponse>>(named(AUTH_MAPPER_NAME)) { AuthLoginMapper() }
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

