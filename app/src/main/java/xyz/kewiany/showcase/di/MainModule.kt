package xyz.kewiany.showcase.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.kewiany.showcase.AppState
import xyz.kewiany.showcase.details.DetailsViewModel
import xyz.kewiany.showcase.details.GetRepositoryDetails
import xyz.kewiany.showcase.details.GetRepositoryDetailsImpl
import xyz.kewiany.showcase.list.GetRepositories
import xyz.kewiany.showcase.list.GetRepositoriesImpl
import xyz.kewiany.showcase.list.ListViewModel
import xyz.kewiany.showcase.mainNavController
import xyz.kewiany.showcase.splash.SplashViewModel
import xyz.kewiany.showcase.utils.DefaultDispatcherProvider
import xyz.kewiany.showcase.utils.NavigationCommander
import xyz.kewiany.showcase.utils.NavigationCommanderImpl

val mainModule = module {
    val state = AppState()
    val dispatchers = DefaultDispatcherProvider
    single<NavigationCommander> { NavigationCommanderImpl(::mainNavController) }
    single<GetRepositories> { GetRepositoriesImpl(dispatchers) }
    single<GetRepositoryDetails> { GetRepositoryDetailsImpl(dispatchers) }
    viewModel { SplashViewModel(get(), dispatchers) }
    viewModel { ListViewModel(state.listState, get(), get(), dispatchers) }
    viewModel { DetailsViewModel(state.detailsState, get(), get(), dispatchers) }
}
