package xyz.kewiany.showcase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import xyz.kewiany.showcase.splash.SplashViewModel
import xyz.kewiany.showcase.utils.NavigationCommander

internal class SplashViewModelTest : CustomFreeSpec({

    "on splash view model test" - {
        val navigationCommander = mock<NavigationCommander>()
        val viewModel = SplashViewModel(navigationCommander, testDispatcherProvider)

        "on create" - {
            viewModel.create()

            "after 1000 milliseconds" - {
                testScope.advanceTimeBy(1000)

                "do not navigate" { verify(navigationCommander, never()).navigate(R.id.action_splashFragment_to_listFragment) }
            }

            "after 1500 milliseconds" - {
                testScope.advanceTimeBy(1500)

                "navigate" { verify(navigationCommander).navigate(R.id.action_splashFragment_to_listFragment) }
            }
        }
    }
})
