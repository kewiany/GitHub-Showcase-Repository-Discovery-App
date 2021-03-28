package xyz.kewiany.showcase.splash

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import xyz.kewiany.showcase.CustomFreeSpec
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.NavigationCommander

internal class SplashViewModelTest : CustomFreeSpec({

    "on splash view model test" - {
        val navigationCommander = mock<NavigationCommander>()
        SplashViewModel(navigationCommander, testDispatcherProvider)

        "after 1000 milliseconds" - {
            testScope.advanceTimeBy(1000)

            "do not navigate" { verify(navigationCommander, never()).navigate(R.id.action_splashFragment_to_listFragment) }
        }

        "after 5000 milliseconds" - {
            testScope.advanceTimeBy(5000)

            "navigate" { verify(navigationCommander).navigate(R.id.action_splashFragment_to_listFragment) }
        }
    }
})
