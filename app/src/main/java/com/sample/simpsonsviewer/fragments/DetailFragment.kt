package com.sample.simpsonsviewer.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import com.bumptech.glide.Glide
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.databinding.FragmentDetailBinding
import com.sample.simpsonsviewer.models.Character
import com.sample.simpsonsviewer.models.DuckDuckGoUIObservable
import com.sample.simpsonsviewer.viewModels.DuckDuckGoViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Fragment for detail page
 */
class DetailFragment: BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {
    private val viewModel: DuckDuckGoViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.duckDuckGoData.observe(viewLifecycleOwner) {
            if (it.process.isOnDetail()) {
                processDetailResponse(it)
            }
        }
    }

    private fun processDetailResponse(response: DuckDuckGoUIObservable) {
        when {
            response.state.isProcessing() -> {
                // Show loading progress bar
            }
            response.state.isDone() -> {
                response.result?.selectedCharacter?.let {
                    showCharacter(it)
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showCharacter(character: Character) {
        with(binding.description) {
            text = Html.fromHtml(
                character.htmlDescription,
                Html.FROM_HTML_MODE_LEGACY
            )
            movementMethod = LinkMovementMethod.getInstance()
        }

        Glide.with(requireContext())
            .load(character.icon)
            .error(resources.getDrawable(R.drawable.ic_placeholder, null))
            .into(binding.image)
    }
}