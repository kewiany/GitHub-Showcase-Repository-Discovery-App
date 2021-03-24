package xyz.kewiany.showcase.di

import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import xyz.kewiany.showcase.mainNavController
import xyz.kewiany.showcase.splash.SplashViewModel
import xyz.kewiany.showcase.utils.NavigationCommander
import xyz.kewiany.showcase.utils.NavigationCommanderImpl

val mainModule = module {
    single<NavigationCommander> { NavigationCommanderImpl(::mainNavController) }
    viewModel<SplashViewModel>()
}
