package xyz.kewiany.showcase.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.setStandardScreenMode

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setStandardScreenMode()
        with(lifecycleScope) {
            launch { viewModel.isLoading.collect { updateIsLoading(it) } }
            launch { viewModel.name.collect { updateName(it) } }
            launch { viewModel.description.collect { updateDescription(it) } }
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        println("isLoading $isLoading")
    }

    private fun updateName(name: String) {
        println("name $name")
    }

    private fun updateDescription(description: String) {
        println("description $description")
    }
}