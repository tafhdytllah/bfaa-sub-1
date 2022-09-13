package com.tafh.githubuserapp.adapter

import User
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tafh.githubuserapp.databinding.ItemRowUserBinding

class ListUserAdapter(private val list: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    inner class ListUserViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.apply {
                tvItemName.text = item.name
                tvItemUsername.text = item.username
                Glide.with(binding.root.context)
                    .load(item.avatar)
                    .circleCrop()
                    .apply(RequestOptions().override(64, 64))
                    .into(binding.ivItemAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

}