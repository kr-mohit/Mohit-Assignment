package com.example.androidassignment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidassignment.databinding.ItemPostBinding
import com.example.androidassignment.domain.model.Post

class PostAdapter(
    private val posts: List<Post>,
    private val onPostClick: (Post) -> Unit,
): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    inner class PostViewHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            Glide.with(binding.root.context).load(post.imageUrl).into(binding.ivMedia)
            Glide.with(binding.root.context).load(post.profileImageUrl).into(binding.ivProfilePicture)
            binding.tvUsername.text = post.username
            binding.tvTitle.text = post.title
            binding.tvPostedOn.text = post.timeAgo
            binding.root.setOnClickListener { onPostClick(post) }
        }
    }
}