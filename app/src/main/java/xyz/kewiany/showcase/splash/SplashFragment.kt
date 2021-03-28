package xyz.kewiany.showcase.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.splash_fragment.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.setFullScreenMode
import kotlin.random.Random

class SplashFragment : Fragment(R.layout.splash_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setFullScreenMode()
        getViewModel<SplashViewModel>().viewModelScope.launch {
            while (true) {
                splashProgressView?.rotation = Random.nextInt(0, 360).toFloat()
                delay(LAUNCHER_PROGRESS_VIEW_ROTATION_IN_MILLI_SECONDS)
            }
        }
        splashTextView.text = getString(R.string.app_name)
    }

    companion object {
        const val LAUNCHER_PROGRESS_VIEW_ROTATION_IN_MILLI_SECONDS = 100L
        const val SPLASH_SCREEN_DURATION_IN_MS = 5000L
    }
}