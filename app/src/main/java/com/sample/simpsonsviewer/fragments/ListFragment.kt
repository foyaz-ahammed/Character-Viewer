package com.sample.simpsonsviewer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.adapter.CharacterListAdapter
import com.sample.simpsonsviewer.databinding.FragmentListBinding
import com.sample.simpsonsviewer.models.DuckDuckGoUIObservable
import com.sample.simpsonsviewer.utility.getItemFoundDescription
import com.sample.simpsonsviewer.viewModels.DuckDuckGoViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: DuckDuckGoViewModel by sharedViewModel()

    private val adapter = CharacterListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        setUpObservers()
        if (savedInstanceState == null) {
            viewModel.getCharacters()
        }
    }

    private fun setUpBinding() {
        binding.characterListView.adapter = adapter
        binding.inputKeyword.doAfterTextChanged {
            viewModel.searchCharacters(it.toString())
        }
        binding.buttonRetry.setOnClickListener {
            viewModel.getCharacters()
        }
        adapter.setItemClickListener { character ->
            val detailContainer = activity?.findViewById<View>(R.id.detail_container)
            if (detailContainer != null) { // Tablet
                val detailFragment = DetailFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.detail_container, detailFragment)
                    .commit()
            } else { // Phone
                findNavController().navigate(R.id.action_listFragment_to_detailFragment)
            }

            viewModel.setSelectedCharacter(character)
        }
    }

    private fun setUpObservers() {
        viewModel.duckDuckGoData.observe(viewLifecycleOwner) {
            if (it.process.isOnListItem()) {
                processListItemResponse(it)
            }
        }
    }

    private fun processListItemResponse(response: DuckDuckGoUIObservable) {
        when {
            response.state.isProcessing() -> {
                binding.progressBar.isVisible = true
                binding.contentViews.isVisible = false
                binding.errorContentViews.isVisible = false
            }
            response.state.isDone() -> {
                binding.progressBar.isVisible = false
                response.error?.let {
                    binding.errorContentViews.isVisible = true
                    binding.contentViews.isVisible = false
                }?: run {
                    response.result?.characterList?.let { characterList ->
                        binding.contentViews.isVisible = true
                        binding.errorContentViews.isVisible = false
                        binding.itemFoundDescription.text = characterList.size.getItemFoundDescription(resources)
                        adapter.submitList(characterList)
                    }
                }
            }
        }
    }
}