package com.tafh.githubuserapp.view.fragment

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.data.model.User
import com.tafh.githubuserapp.databinding.FragmentDetailUserBinding

class DetailUserFragment : Fragment(R.layout.fragment_detail_user) {

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

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
        setUiFromParcelable()

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

        setHasOptionsMenu(true)
    }

    private fun setUiFromParcelable() {
        if (arguments != null) {
            val user = arguments?.getParcelable<User>(EXTRA_DATA)!!

            binding.apply {
                val imageUri = user.avatar
                val packageName = requireContext().packageName
                val image = resources.getIdentifier(imageUri, null, packageName)
                Glide.with(requireContext())
                    .load(image)
                    .circleCrop()
                    .apply(RequestOptions().override(128, 128))
                    .into(ivAvatarDetailUser)

                tvNameDetailUser.text = user.name
                tvUsernameDetailUser.text = user.username
                tvRepositoryDetailUser.text = user.repository.toString()
                tvFollowerDetailUser.text = user.follower.toString()
                tvFollowingDetailUser.text = user.following.toString()
                tvLocationDetailUser.text = user.location
                tvCompanyDetailUser.text = user.company
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareIntent()
                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


}

