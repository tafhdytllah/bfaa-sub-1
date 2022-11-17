package com.tafh.githubuserapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.adapters.DetailUserPagerAdapter.Companion.ARG_USERNAME_DETAIL
import com.tafh.githubuserapp.adapters.UserAdapter
import com.tafh.githubuserapp.data.remote.response.User
import com.tafh.githubuserapp.databinding.FragmentFollowerBinding
import com.tafh.githubuserapp.viewmodel.DetailUserViewModel

class FollowerFragment : Fragment(R.layout.fragment_follower) {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private val followerViewModel by viewModels<DetailUserViewModel>()

    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followerViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        followerViewModel.isEmpty.observe(viewLifecycleOwner) {
            showEmptyData(it)
        }

        val username = arguments?.getString(ARG_USERNAME_DETAIL, "").toString()
        followerViewModel.getUserFollower(username)

        followerViewModel.userFollower.observe(viewLifecycleOwner) { listUser ->
            setUserRecyclerView(listUser)
        }
    }

    private fun setUserRecyclerView(listUser: List<User>) {
        binding.rvListUserFollower.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            userAdapter = UserAdapter(listUser)
            adapter = userAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBarFollower.visibility = View.VISIBLE
                tvEmptyFollower.visibility = View.GONE
            } else {
                progressBarFollower.visibility = View.GONE
                rvListUserFollower.visibility = View.VISIBLE
            }
        }
    }

    private fun showEmptyData(isEmpty: Boolean) {
        binding.apply {
            if (isEmpty) {
                tvEmptyFollower.visibility = View.VISIBLE
                rvListUserFollower.visibility = View.GONE
            } else {
                tvEmptyFollower.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}