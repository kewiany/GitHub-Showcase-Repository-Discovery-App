package xyz.kewiany.showcase.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.kewiany.showcase.AppState
import xyz.kewiany.showcase.list.ListViewModel
import xyz.kewiany.showcase.mainNavController
import xyz.kewiany.showcase.splash.SplashViewModel
import xyz.kewiany.showcase.utils.DefaultDispatcherProvider
import xyz.kewiany.showcase.utils.NavigationCommander
import xyz.kewiany.showcase.utils.NavigationCommanderImpl

val mainModule = module {
    val state = AppState()
    single<NavigationCommander> { NavigationCommanderImpl(::mainNavController) }
    viewModel { SplashViewModel(get(), DefaultDispatcherProvider) }
    viewModel { ListViewModel(state.listState) }
}
