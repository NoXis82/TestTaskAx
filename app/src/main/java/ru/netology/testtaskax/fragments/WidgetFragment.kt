package ru.netology.testtaskax.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.testtaskax.R
import ru.netology.testtaskax.adapter.CommentAdapter
import ru.netology.testtaskax.adapter.IOnActionListener
import ru.netology.testtaskax.databinding.FragmentWidgetBinding
import ru.netology.testtaskax.dto.CommentDto
import ru.netology.testtaskax.viewmodel.CommentViewModel

class WidgetFragment : Fragment() {
    private val viewModel: CommentViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWidgetBinding.inflate(layoutInflater)
        val adapter = CommentAdapter(object : IOnActionListener {
            override fun onClickComment(comment: CommentDto) {
                viewModel.clickComment(comment, findNavController())
            }
        })
        binding.rvCommentsView.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(viewModel.equalsLists(it))
        }
        viewModel.timer.observe(viewLifecycleOwner) {
            binding.tvTimer.text = requireContext().getString(R.string.title_timer, it.toString())
        }
        return binding.root
    }

}