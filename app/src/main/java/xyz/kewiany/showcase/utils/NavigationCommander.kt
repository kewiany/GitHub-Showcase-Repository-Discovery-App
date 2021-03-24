package xyz.kewiany.showcase.utils

import androidx.navigation.NavController

interface NavigationCommander {
    fun popBackStack()
    fun navigate(destinationId: Int)
}

class NavigationCommanderImpl(private val getNavController: () -> NavController?) : NavigationCommander {

    override fun navigate(destinationId: Int) {
        getNavController()?.takeIf { it.currentDestination?.id != destinationId }?.run { navigate(destinationId) }
    }

    override fun popBackStack() {
        getNavController()?.popBackStack()
    }
}
