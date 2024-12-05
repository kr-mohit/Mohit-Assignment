package com.example.androidassignment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
import com.example.androidassignment.databinding.ItemHashtagBinding
import com.example.androidassignment.domain.model.Post
import com.example.androidassignment.domain.model.TrendingHashtag

class TrendingPostsAdapter(
    private val onPostClick: (Post) -> Unit,
    private val onSeeAllClick: (TrendingHashtag) -> Unit
): ListAdapter<TrendingHashtag, TrendingPostsAdapter.TrendingPostsViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingPostsViewHolder {
        val binding = ItemHashtagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingPostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingPostsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class TrendingPostsViewHolder(private val binding: ItemHashtagBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingHashtag: TrendingHashtag) {
            binding.tvHashtag.text = binding.root.context.getString(R.string.hashtags, trendingHashtag.hashtag)
            binding.ctaSeeAll.setOnClickListener { onSeeAllClick(trendingHashtag) }
            binding.rvPosts.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = PostAdapter(trendingHashtag.posts) { onPostClick(it) }
            }
        }
    }

    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<TrendingHashtag>() {
        override fun areItemsTheSame(oldItem: TrendingHashtag, newItem: TrendingHashtag): Boolean {
            return oldItem.hashtag == newItem.hashtag
        }

        override fun areContentsTheSame(oldItem: TrendingHashtag, newItem: TrendingHashtag): Boolean {
            return oldItem == newItem
        }
    }
}