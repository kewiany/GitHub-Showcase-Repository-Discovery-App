package xyz.kewiany.showcase.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.setFullScreenMode

class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setFullScreenMode()
    }

    companion object {
        const val SPLASH_SCREEN_DURATION_IN_MS = 1500L
    }
}