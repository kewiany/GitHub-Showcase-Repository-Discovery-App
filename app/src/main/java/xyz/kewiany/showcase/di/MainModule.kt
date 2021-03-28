package xyz.kewiany.showcase.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.threeten.bp.Clock
import xyz.kewiany.showcase.AppState
import xyz.kewiany.showcase.BuildConfig
import xyz.kewiany.showcase.api.RepositoryApi
import xyz.kewiany.showcase.api.RepositoryService
import xyz.kewiany.showcase.api.UserApi
import xyz.kewiany.showcase.api.UserService
import xyz.kewiany.showcase.details.*
import xyz.kewiany.showcase.list.GetRepositories
import xyz.kewiany.showcase.list.GetRepositoriesImpl
import xyz.kewiany.showcase.list.ListViewModel
import xyz.kewiany.showcase.mainNavController
import xyz.kewiany.showcase.splash.SplashViewModel
import xyz.kewiany.showcase.utils.*

val mainModule = module {
    val state = AppState()
    val dispatchers = DefaultDispatcherProvider
    single<NavigationCommander> { NavigationCommanderImpl(::mainNavController) }
    single<DateTimeFormatter> { DateTimerFormatterImpl(Clock.systemDefaultZone(), androidContext()) }
    val path = BuildConfig.API_BASE_URL
    single<RepositoryApi> { RepositoryService(path) }
    single<UserApi> { UserService("$path/users") }
    single<GetRepositories> { GetRepositoriesImpl(get(), dispatchers) }
    single<GetRepositoryDetails> { GetRepositoryDetailsImpl(get(), dispatchers) }
    single<GetUser> { GetUserImpl(get(), dispatchers) }
    viewModel { SplashViewModel(get(), dispatchers) }
    viewModel { ListViewModel(state.listState, get(), get(), dispatchers) }
    viewModel { DetailsViewModel(state.detailsState, get(), get(), get(), get(), dispatchers) }
}
