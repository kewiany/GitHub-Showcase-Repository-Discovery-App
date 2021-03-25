package xyz.kewiany.showcase.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.splash.SplashFragment.Companion.SPLASH_SCREEN_DURATION_IN_MS
import xyz.kewiany.showcase.utils.DispatcherProvider
import xyz.kewiany.showcase.utils.NavigationCommander

class SplashViewModel(
    private val navigationCommander: NavigationCommander,
    dispatcherProvider: DispatcherProvider
) : ViewModel() {

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            delay(SPLASH_SCREEN_DURATION_IN_MS)
            navigationCommander.navigate(R.id.action_splashFragment_to_listFragment)
        }
    }
}