package com.example.githubrepos.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepos.databinding.ItemUserBinding
import com.example.githubrepos.domain.model.User

class HomeAdapter(private val movies: List<User>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemUserBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            with(binding) {
                txtName.text = item.login

                Glide
                    .with(binding.root)
                    .load(item.avatarUrl)
                    .centerCrop()
                    .into(imgCover)
            }
        }
    }
}