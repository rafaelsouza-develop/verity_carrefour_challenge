package com.example.githubrepos.presentation.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepos.databinding.ItemUserBinding
import com.example.githubrepos.domain.model.Repos
import com.example.githubrepos.domain.model.User

class ReposAdapter(private val repos: List<Repos>) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemUserBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount() = repos.size

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Repos) {
            with(binding) {
                txtName.text = item.name
            }
        }
    }


}