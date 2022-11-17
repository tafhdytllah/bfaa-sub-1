package com.tafh.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.adapters.DetailUserPagerAdapter.Companion.ARG_USERNAME_DETAIL
import com.tafh.githubuserapp.adapters.UserAdapter
import com.tafh.githubuserapp.data.remote.response.User
import com.tafh.githubuserapp.databinding.FragmentFollowerBinding
import com.tafh.githubuserapp.databinding.FragmentFollowingBinding
import com.tafh.githubuserapp.viewmodel.DetailUserViewModel

class FollowingFragment : Fragment(R.layout.fragment_following) {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private val followingViewModel by viewModels<DetailUserViewModel>()

    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME_DETAIL, "").toString()
        followingViewModel.getUserFollowing(username)

        followingViewModel.userFollowing.observe(viewLifecycleOwner) { listUser ->
            setUserRecyclerView(listUser)
        }
    }

    private fun setUserRecyclerView(listUser: List<User>) {
        binding.rvListUserFollowing.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            userAdapter = UserAdapter(listUser)
            adapter = userAdapter
        }
    }

}