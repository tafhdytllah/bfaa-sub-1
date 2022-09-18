package com.tafh.githubuserapp.view.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.data.model.User
import com.tafh.githubuserapp.databinding.FragmentDetailUserBinding
import com.tafh.githubuserapp.view.activity.MainActivity


class DetailUserFragment : Fragment(R.layout.fragment_detail_user) {

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var user: User
    private var titleDetailUser = ""
    private lateinit var actionBar: ActionBar

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

        actionBar = (activity as MainActivity).supportActionBar!!
        actionBar.apply {
            title = titleDetailUser
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

    private fun setUiFromParcelable() {
        if (arguments != null) {
            user = arguments?.getParcelable(EXTRA_DATA)!!
            titleDetailUser = user.username
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

