package xyz.kewiany.showcase.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.setStandardScreenMode
import xyz.kewiany.showcase.utils.snackBar
import xyz.kewiany.showcase.utils.translate

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setStandardScreenMode()
        with(lifecycleScope) {
            launch { viewModel.isLoading.collect { updateIsLoading(it) } }
            launch { viewModel.name.collect { updateName(it) } }
            launch { viewModel.description.collect { updateDescription(it) } }
            launch { viewModel.error.collect { updateError((it)) } }
        }
        detailsBackButton.setOnClickListener { viewModel.back() }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        detailsSwipeRefreshLayout.isRefreshing = isLoading
    }

    private fun updateName(name: String) {
    }

    private fun updateDescription(description: String) {
    }

    private fun updateError(errorType: ErrorType?) {
        errorType ?: return
        detailsCoordinatorLayout.snackBar(errorType.translate(requireContext()))
    }
}