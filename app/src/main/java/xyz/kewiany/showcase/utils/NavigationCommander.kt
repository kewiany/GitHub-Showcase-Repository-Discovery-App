package xyz.kewiany.showcase.utils

import androidx.core.os.bundleOf
import androidx.navigation.NavController

interface NavigationCommander {
    fun popBackStack()
    fun navigate(destinationId: Int)
    fun navigate(destinationId: Int, bundle: Pair<String, Long>)
}

class NavigationCommanderImpl(private val getNavController: () -> NavController?) : NavigationCommander {

    override fun popBackStack() {
        getNavController()?.popBackStack()
    }

    override fun navigate(destinationId: Int) {
        getNavController()?.takeIf { it.currentDestination?.id != destinationId }?.run { navigate(destinationId) }
    }

    override fun navigate(destinationId: Int, bundle: Pair<String, Long>) {
        val (key, value) = bundle
        getNavController()?.takeIf { it.currentDestination?.id != destinationId }?.run { navigate(destinationId, bundleOf(key to value)) }
    }
}
