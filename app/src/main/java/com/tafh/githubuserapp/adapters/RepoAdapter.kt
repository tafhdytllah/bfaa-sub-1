package com.tafh.githubuserapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tafh.githubuserapp.data.remote.response.Repository
import com.tafh.githubuserapp.databinding.ItemRowRepositoryBinding

class RepoAdapter(private val list: List<Repository>) :
    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {


    inner class RepoViewHolder(private val binding: ItemRowRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            binding.apply {
                tvNameItemRepository.text = item.name.toString()
                tvVisibilityItemRepo.text = item.visibility.toString()
                tvLanguageItemRepository.text = item.language
                tvUpdateAtItemRepository.text = item.updateAt
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepoViewHolder(
            ItemRowRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}