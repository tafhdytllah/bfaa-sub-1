package com.tafh.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tafh.githubuserapp.databinding.ItemRowUserBinding
//
//class ListUserAdapter(private val list: List<User>) :
//    RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {
//
//    private lateinit var onItemClickCallback: OnItemClickCallback
//
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
//
//    inner class ListUserViewHolder(private val binding: ItemRowUserBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(item: User) {
//
//            binding.apply {
//                val imageUri = item.avatar
//                val packageName = root.context.packageName
//                val image = root.resources.getIdentifier(imageUri, null, packageName)
//                Glide.with(binding.root.context)
//                    .load(image)
//                    .circleCrop()
//                    .apply(RequestOptions().override(96, 96))
//                    .into(binding.ivItemAvatar)
//
//                tvItemName.text = item.name
//                tvItemUsername.text = item.username
//
//                root.setOnClickListener {
//                    onItemClickCallback.onItemClicked(item)
//                }
//
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ListUserViewHolder(
//            ItemRowUserBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
//        holder.bind(list[position])
//    }
//
//    override fun getItemCount() = list.size
//
//    interface OnItemClickCallback {
//        fun onItemClicked(data: User)
//    }
//
//}