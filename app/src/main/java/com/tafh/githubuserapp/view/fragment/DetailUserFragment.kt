package com.tafh.githubuserapp.view.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.databinding.FragmentDetailUserBinding
import com.tafh.githubuserapp.viewmodel.DetailUserViewModel

private const val TAG = "DetailUserFragment"

class DetailUserFragment : Fragment(R.layout.fragment_detail_user) {

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    companion object {
        const val EXTRA_DATA = "extra_data"
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

        binding.toolbarDetail.apply {
            setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }

        val username = DetailUserFragmentArgs.fromBundle(arguments as Bundle).usernameDetail
        Log.d(TAG, "onViewCreated: $username")
        detailUserViewModel.getDetailUser(username)

        detailUserViewModel.userDetail.observe(viewLifecycleOwner) { user ->
            binding.apply {
                toolbarDetail.title = user.login

                Glide.with(requireContext())
                    .load(user.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.img_github_logo)
                    .into(ivAvatarDetailUser)

                tvNameDetailUser.text = user.name
                tvRepositoryDetailUser.text = user.repositories.toString()
                tvFollowerDetailUser.text = user.followers.toString()
                tvFollowingDetailUser.text = user.following.toString()
                tvLocationDetailUser.text = user.location ?: "-"
                tvCompanyDetailUser.text = user.company ?: "-"

            }
        }

        binding.apply {
            btnFollowDetailUser.setOnCheckedChangeListener { _,isChecked ->
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

        this.setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

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

