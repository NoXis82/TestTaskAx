package ru.netology.testtaskax.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.netology.testtaskax.App
import ru.netology.testtaskax.R
import ru.netology.testtaskax.adapter.CommentAdapter
import ru.netology.testtaskax.adapter.IOnActionListener
import ru.netology.testtaskax.databinding.FragmentWidgetBinding
import ru.netology.testtaskax.dto.CommentDto
import ru.netology.testtaskax.viewmodel.CommentViewModel
import javax.inject.Inject

class WidgetFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CommentViewModel::class.java)
        val binding = FragmentWidgetBinding.inflate(layoutInflater)
        val adapter = CommentAdapter(object : IOnActionListener {
            override fun onClickComment(comment: CommentDto) {
                viewModel.clickComment(comment, findNavController())
            }
        })
        binding.rvCommentsView.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { list ->
            adapter.submitList(viewModel.equalsLists(list))
        }
        viewModel.timer.observe(viewLifecycleOwner) {time ->
            binding.tvTimer.text = requireContext().getString(R.string.title_timer, time.toString())
        }
        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            binding.pbProgressLoad.isVisible = state.loading
            if (state.error) {
                Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry_loading) { viewModel.load() }
                    .show()
            }
        }
        return binding.root
    }

}