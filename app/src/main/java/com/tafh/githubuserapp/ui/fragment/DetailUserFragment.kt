package com.tafh.githubuserapp.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.adapters.DetailUserPagerAdapter
import com.tafh.githubuserapp.databinding.FragmentDetailUserBinding
import com.tafh.githubuserapp.viewmodel.DetailUserViewModel


class DetailUserFragment : Fragment(R.layout.fragment_detail_user) {

    private val TAG = "DetailUserFragment"
    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!
    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_1, R.string.tab_2, R.string.tab_3)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailUserViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        detailUserViewModel.isEmpty.observe(viewLifecycleOwner) {
            showEmptyData(it)
        }

        binding.toolbarDetailUser.apply {
            setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }

        val username = DetailUserFragmentArgs.fromBundle(arguments as Bundle).usernameDetail
        detailUserViewModel.getDetailUser(username)

        detailUserViewModel.userDetail.observe(viewLifecycleOwner) { user ->
            binding.apply {
                toolbarDetailUser.title = user.login

                Glide.with(requireContext())
                    .load(user.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.img_github_logo)
                    .into(ivAvatarDetailUser)

                tvNameDetailUser.text = user.name ?: "-"
                tvRepositoryDetailUser.text = user.repositories.toString()
                tvFollowerDetailUser.text = user.followers.toString()
                tvFollowingDetailUser.text = user.following.toString()
                tvLocationDetailUser.text = user.location ?: "-"
                tvCompanyDetailUser.text = user.company ?: "-"

            }
        }


        binding.apply {
            btnFollowDetailUser.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) {
                    btnFollowDetailUser.isChecked = false
                    Toast.makeText(requireContext(), "Unfollow", Toast.LENGTH_SHORT).show()
                    btnFollowDetailUser.apply {
                        setTextColor(Color.parseColor("#FFFFFFFF"))
                        setBackgroundResource(R.drawable.bg_follow)
                    }
                } else {
                    btnFollowDetailUser.isChecked = true
                    Toast.makeText(requireContext(), "Follow", Toast.LENGTH_SHORT).show()
                    btnFollowDetailUser.apply {
                        setTextColor(Color.parseColor("#FF000000"))
                        setBackgroundResource(R.drawable.bg_unfollow)
                    }
                }
            }
        }

        val detailUserPagerAdapter = DetailUserPagerAdapter(activity as AppCompatActivity, username)

        val viewPager: ViewPager2 = (activity as AppCompatActivity).findViewById(R.id.view_pager)
        viewPager.adapter = detailUserPagerAdapter

        val tabs: TabLayout = (activity as AppCompatActivity).findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        this.setHasOptionsMenu(true)
    }

    private fun showEmptyData(isEmpty: Boolean) {
        binding.apply {
            if (isEmpty) {
                tvNofoundDetailUser.visibility = View.VISIBLE
            } else {
                tvNofoundDetailUser.visibility = View.GONE
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBarDetailUser.visibility = View.VISIBLE
            } else {
                progressBarDetailUser.visibility = View.GONE
            }
        }
    }

    private fun shareIntent() {
        try {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, """
                    Aplikasi Github User
                    Dibuat oleh Taufik Hidayatullah
                """.trimIndent()
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                shareIntent()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

