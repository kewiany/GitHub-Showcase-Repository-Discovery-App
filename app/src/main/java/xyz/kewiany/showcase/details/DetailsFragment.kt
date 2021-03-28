package xyz.kewiany.showcase.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.entity.User
import xyz.kewiany.showcase.list.toFormattedCreatedDate
import xyz.kewiany.showcase.list.toFormattedUpdatedDate
import xyz.kewiany.showcase.utils.Constant.REPOSITORY_KEY
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.setStandardScreenMode
import xyz.kewiany.showcase.utils.snackBar
import xyz.kewiany.showcase.utils.translate

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel by viewModel<DetailsViewModel>()
    private val id: Long? by lazy { arguments?.getLong(REPOSITORY_KEY) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setStandardScreenMode()
        with(lifecycleScope) {
            launch { viewModel.isLoading.collect { updateIsLoading(it) } }
            launch { viewModel.name.collect { updateName(it) } }
            launch { viewModel.description.collect { updateDescription(it) } }
            launch { viewModel.stars.collect { updateStars(it) } }
            launch { viewModel.watchers.collect { updateWatchers(it) } }
            launch { viewModel.forks.collect { updateForks(it) } }
            launch { viewModel.updatedAt.collect { updateUpdatedAt(it) } }
            launch { viewModel.createdAt.collect { updateCreatedAt(it) } }
            launch { viewModel.language.collect { updateLanguage(it) } }
            launch { viewModel.owner.collect { updateOwner(it) } }
            launch { viewModel.avatar.collect { updateAvatar(it) } }
            launch { viewModel.followers.collect { updateUserFollowers(it) } }
            launch { viewModel.error.collect { updateError(it) } }
        }
        detailsBackButton.setOnClickListener { viewModel.back() }
        detailsSwipeRefreshLayout.setOnRefreshListener { viewModel.load(requireNotNull(id)) }
        viewModel.load(requireNotNull(id))
    }

    private fun updateIsLoading(isLoading: Boolean) {
        detailsSwipeRefreshLayout?.isRefreshing = isLoading
    }

    private fun updateName(name: String) {
        detailsRepositoryNameTextView.text = name
    }

    private fun updateDescription(description: String) {
        detailsRepositoryNameTextView.text = description
    }

    private fun updateStars(stars: String) {
        detailsRepositoryStarsImageView.isVisible = stars.isNotEmpty()
        detailsRepositoryStarsTextView.text = stars
    }

    private fun updateWatchers(watchers: String) {
        detailsRepositoryWatchersImageView.isVisible = watchers.isNotEmpty()
        detailsRepositoryWatchersTextView.text = watchers
    }

    private fun updateForks(forks: String) {
        detailsRepositoryForksImageView.isVisible = forks.isNotEmpty()
        detailsRepositoryForksTextView.text = forks
    }

    private fun updateUpdatedAt(updatedAt: String) {
        detailsRepositoryUpdatedAt.text = updatedAt.toFormattedUpdatedDate(requireContext())
    }

    private fun updateCreatedAt(createdAt: String) {
        detailsRepositoryCreatedAt.text = createdAt.toFormattedCreatedDate(requireContext())
    }

    private fun updateLanguage(language: String) {
        detailsRepositoryLanguageTextView.text = language
    }

    private fun updateOwner(owner: String) {
        detailsRepositoryOwnerTextView.text = owner
    }

    private fun updateAvatar(avatar: String) {
        Glide.with(requireContext())
            .load(avatar)
            .circleCrop()
            .into(detailsRepositoryOwnerImageView)
    }

    private fun updateUserFollowers(followers: List<User>) {
        detailsRepositoryOwnerFollowersHeaderTextView.text = getString(R.string.followers_count, followers.count().toString())
        followers.forEach {
            FollowerItemView(requireContext()).apply {
                avatar = it.avatar; name = it.name
            }.also(detailsRepositoryOwnerFollowersLinearLayout::addView)
        }
    }

    private fun updateError(errorType: ErrorType?) {
        errorType ?: return
        detailsCoordinatorLayout.snackBar(errorType.translate(requireContext()))
    }
}