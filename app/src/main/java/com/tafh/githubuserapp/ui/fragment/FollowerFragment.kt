package com.tafh.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.adapters.DetailUserPagerAdapter.Companion.ARG_USERNAME_DETAIL
import com.tafh.githubuserapp.databinding.FragmentFollowerBinding
import com.tafh.githubuserapp.viewmodel.DetailUserViewModel

class FollowerFragment : Fragment(R.layout.fragment_follower) {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private val followerViewModel by viewModels<DetailUserViewModel>()

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

        val username = arguments?.getString(ARG_USERNAME_DETAIL, "").toString()
        binding.tvText.text = username.toString()

        followerViewModel.getUserFollower(username)
        followerViewModel.userFollower.observe(viewLifecycleOwner) {
            binding.tvText.text = it.toString()
        }
    }

}