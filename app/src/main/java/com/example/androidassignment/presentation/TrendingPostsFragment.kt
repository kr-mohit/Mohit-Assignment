package com.example.androidassignment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassignment.databinding.FragmentTrendingPostsBinding
import com.example.androidassignment.domain.model.TrendingHashtag
import com.example.androidassignment.utils.Response
import com.example.androidassignment.utils.Utils.hide
import com.example.androidassignment.utils.Utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingPostsFragment : Fragment() {

    private lateinit var binding: FragmentTrendingPostsBinding
    private val mViewModel: TrendingPostsViewModel by viewModels()
    private lateinit var trendingPostsAdapter: TrendingPostsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTrendingPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initUI()
        initObserver()
    }

    private fun initAdapter() {
        trendingPostsAdapter = TrendingPostsAdapter(
            onSeeAllClick = { hashtag ->
                Toast.makeText(context, "See all clicked for \"${hashtag.hashtag}\"", Toast.LENGTH_SHORT).show()
            },
            onPostClick = { post ->
                Toast.makeText(context, "Post \"${post.title}\" clicked", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvTrendingHashtags.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = trendingPostsAdapter
        }
    }

    private fun initUI() {
        mViewModel.fetchTrendingPosts()
    }

    private fun initObserver() {
        mViewModel.trendingPosts.observe(viewLifecycleOwner) {
            handleTrendingPostsResponse(it)
        }
    }

    private fun handleTrendingPostsResponse(response: Response<List<TrendingHashtag>>) {
        when (response) {
            is Response.Error -> {
                binding.progressBar.hide()
                binding.rvTrendingHashtags.hide()
                Toast.makeText(context, response.error, Toast.LENGTH_SHORT).show()
            }
            is Response.Loading -> {
                binding.progressBar.show()
                binding.rvTrendingHashtags.hide()
            }
            is Response.Success -> {
                binding.progressBar.hide()
                if (response.data == null) {
                    Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                    return
                }
                binding.rvTrendingHashtags.show()
                response.data.let {
                    trendingPostsAdapter.submitList(it)
                }
            }
        }
    }
}