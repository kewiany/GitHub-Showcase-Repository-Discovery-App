package xyz.kewiany.showcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.ext.android.inject
import xyz.kewiany.showcase.utils.NavigationCommander

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private val navigationCommander by inject<NavigationCommander>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainNavController = navHostFragment.findNavController()
    }

    override fun onBackPressed() {
        navigationCommander.popBackStack()
    }
}

var mainNavController: NavController? = null
