package com.tafh.githubuserapp.view.fragment

import User
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tafh.githubuserapp.adapter.ListUserAdapter
import com.tafh.githubuserapp.data.model.UserData
import com.tafh.githubuserapp.databinding.FragmentListUserBinding
import com.tafh.githubuserapp.view.activity.MainActivity

class ListUserFragment : Fragment() {

    private var _binding: FragmentListUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvListUser: RecyclerView
    private var list = arrayListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvListUser.setHasFixedSize(true)
        list.addAll(UserData.listData)

        listUserRecyclerView()
    }

    private fun listUserRecyclerView() {
        binding.rvListUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val listUserAdapter = ListUserAdapter(list)
            adapter = listUserAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}