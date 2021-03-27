package xyz.kewiany.showcase.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.entity.Repository
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.setStandardScreenMode
import xyz.kewiany.showcase.utils.snackBar
import xyz.kewiany.showcase.utils.translate

class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModel<ListViewModel>()
    private val items: MutableList<Repository> = mutableListOf()
    private val onClick: (Long) -> Unit by lazy { viewModel::openDetails }
    private val adapter by lazy { RepositoryAdapter(onClick, items) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setStandardScreenMode()
        with(lifecycleScope) {
            launch { viewModel.isLoading.collect { updateIsLoading(it) } }
            launch { viewModel.items.collect { updateList(it) } }
            launch { viewModel.error.collect { updateError(it) } }
        }
        listSwipeRefreshLayout.setOnRefreshListener { viewModel.load() }
        listRecyclerView.adapter = adapter
        listRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun updateIsLoading(isLoading: Boolean) {
        listSwipeRefreshLayout?.isRefreshing = isLoading
    }

    private fun updateList(repositories: List<Repository>) {
        with(items) { clear(); addAll(repositories) }
        adapter.notifyDataSetChanged()
    }

    private fun updateError(errorType: ErrorType?) {
        errorType ?: return
        listCoordinatorLayout.snackBar(errorType.translate(requireContext()))
    }
}