package ru.netology.testtaskax.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.netology.testtaskax.R
import ru.netology.testtaskax.databinding.FragmentCommentViewBinding
import ru.netology.testtaskax.viewmodel.CommentViewModel
import java.util.*

class CommentViewFragment : Fragment() {
    private val viewModel: CommentViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCommentViewBinding.inflate(layoutInflater)
        val postId = arguments?.let { CommentViewFragmentArgs.fromBundle(it).postId }
        val commentId = arguments?.let { CommentViewFragmentArgs.fromBundle(it).id }
        val nameAuthor = arguments?.let { CommentViewFragmentArgs.fromBundle(it).name }
        val emailAuthor = arguments?.let { CommentViewFragmentArgs.fromBundle(it).email }.orEmpty()
        val bodyComment = arguments?.let { CommentViewFragmentArgs.fromBundle(it).body }
        val dateComment = arguments?.let { CommentViewFragmentArgs.fromBundle(it).date } ?: 0
        binding.apply {
            tvIdComment.text = requireContext().getString(R.string.title_id_comment, commentId)
            tvIdPost.text = requireContext().getString(R.string.title_id_post, postId)
            tvNameAuthor.text = requireContext().getString(R.string.title_name_author, nameAuthor)
            tvEmailAuthor.text =
                requireContext().getString(R.string.title_email_author, emailAuthor)
            tvTitleText.text =
                requireContext().getString(R.string.title_text_view, Date(dateComment).toString())
            tvBodyComment.text = bodyComment
            tvEmailAuthor.setOnClickListener {
                viewModel.onClickEmail(emailAuthor)
            }
        }
        return binding.root
    }
}