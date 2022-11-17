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
import com.tafh.githubuserapp.adapters.RepoAdapter
import com.tafh.githubuserapp.data.remote.response.Repository
import com.tafh.githubuserapp.databinding.FragmentRepositoryBinding
import com.tafh.githubuserapp.viewmodel.DetailUserViewModel

class RepositoryFragment : Fragment(R.layout.fragment_repository) {

    companion object {
        private const val TAG = "RepositoryFragment"
    }

    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    private val repositoryViewModel by viewModels<DetailUserViewModel>()

    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME_DETAIL, "").toString()
        repositoryViewModel.getUserRepo(username)

        repositoryViewModel.userRepo.observe(viewLifecycleOwner) { listRepo ->
            setRepoRecyclerView(listRepo)
        }

    }

    private fun setRepoRecyclerView(listRepo: List<Repository>) {
        binding.apply {
            rvListRepo.apply{
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                repoAdapter = RepoAdapter(listRepo)
                adapter = repoAdapter
            }
        }
    }

}