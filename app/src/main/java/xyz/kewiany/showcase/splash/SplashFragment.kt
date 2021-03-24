package xyz.kewiany.showcase.splash

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kewiany.showcase.R

class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFullScreen()
    }

    private fun setUpFullScreen() {
        activity?.window?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setDecorFitsSystemWindows(false)
                insetsController?.apply {
                    hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            } else {
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }
    }

    companion object {
        const val SPLASH_SCREEN_DURATION_IN_MS = 1500L
    }
}