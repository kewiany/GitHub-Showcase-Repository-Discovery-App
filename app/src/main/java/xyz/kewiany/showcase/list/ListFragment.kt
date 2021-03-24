package xyz.kewiany.showcase.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.setStandardScreenMode

class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModel<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setStandardScreenMode()
        with(lifecycleScope) {
            launch { viewModel.isLoading.collect { updateIsLoading(it) } }
            launch { viewModel.items.collect { updateList(it) } }
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        println("isLoading $isLoading")
    }

    private fun updateList(items: List<Repository>) {
        println("items $items")
    }
}