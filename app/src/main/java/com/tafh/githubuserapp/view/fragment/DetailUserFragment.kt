package com.tafh.githubuserapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        if (arguments != null) {
            val user = arguments?.getParcelable<User>(EXTRA_DATA)
            binding.tvUsername.text = user.toString()
        }

    }
}