package ru.netology.testtaskax.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import ru.netology.testtaskax.App
import ru.netology.testtaskax.R
import ru.netology.testtaskax.adapter.CommentAdapter
import ru.netology.testtaskax.adapter.IOnActionListener
import ru.netology.testtaskax.databinding.FragmentWidgetBinding
import ru.netology.testtaskax.di.factory.ViewModelFactory
import ru.netology.testtaskax.di.modules.ViewModelFactoryModule
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
                //              viewModel.clickComment(comment, findNavController())
            }
        })
        binding.rvCommentsView.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)//(viewModel.equalsLists(list))
        }
//        viewModel.timer.observe(viewLifecycleOwner) {time ->
//            binding.tvTimer.text = requireContext().getString(R.string.title_timer, time.toString())
//        }
//        viewModel.state.observe(viewLifecycleOwner) { state ->
//            binding.pbProgressLoad.isVisible = state
//        }
        return binding.root
    }

}