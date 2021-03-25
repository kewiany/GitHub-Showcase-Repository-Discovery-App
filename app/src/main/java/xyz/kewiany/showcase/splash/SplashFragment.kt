package xyz.kewiany.showcase.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.setFullScreenMode

class SplashFragment : Fragment(R.layout.splash_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setFullScreenMode()
        getViewModel<SplashViewModel>()
    }

    companion object {
        const val SPLASH_SCREEN_DURATION_IN_MS = 1500L
    }
}