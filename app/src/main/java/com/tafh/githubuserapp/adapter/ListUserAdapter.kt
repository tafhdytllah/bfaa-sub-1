package com.tafh.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tafh.githubuserapp.data.model.UserItem
import com.tafh.githubuserapp.databinding.ItemRowUserBinding

class ListUserAdapter(private val list: List<UserItem>) :
    RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListUserViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserItem) {

            binding.apply {
                Glide.with(binding.root.context)
                    .load(item.avatarUrl)
                    .circleCrop()
                    .apply(RequestOptions().override(96, 96))
                    .into(binding.ivItemAvatar)

                tvItemName.text = item.login
                tvItemUsername.text = item.htmlUrl

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(item)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListUserViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: UserItem)
    }

}