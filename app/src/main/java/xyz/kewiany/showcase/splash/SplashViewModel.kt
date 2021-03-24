package xyz.kewiany.showcase.splash

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.splash.SplashFragment.Companion.SPLASH_SCREEN_DURATION_IN_MS
import xyz.kewiany.showcase.utils.NavigationCommander

class SplashViewModel(
    private val navigationCommander: NavigationCommander
) : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DURATION_IN_MS)
            navigationCommander.navigate(R.id.action_splashFragment_to_listFragment)
        }
    }
}