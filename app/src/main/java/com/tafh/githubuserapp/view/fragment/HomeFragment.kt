package com.tafh.githubuserapp.view.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.adapter.ListUserAdapter
import com.tafh.githubuserapp.data.model.UserItem
import com.tafh.githubuserapp.databinding.FragmentHomeBinding
import com.tafh.githubuserapp.viewmodel.HomeViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        private const val TAG = "HomeFragment"
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    private lateinit var actionBar: ActionBar

    private lateinit var userAdapter: ListUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarHome.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        homeViewModel.isEmpty.observe(viewLifecycleOwner, {
            showEmptyData(it)
        })

        binding.swSearchUser.apply {
            isIconified = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    if (query != null && query.length >= 3) {
                        binding.rvListUser.scrollToPosition(0)
                        homeViewModel.querySearchUser(query)

                        homeViewModel.users.observe(viewLifecycleOwner) { user ->
                            binding.rvListUser.setHasFixedSize(true)
                            setUserRecyclerView(user)
                        }

                        binding.rvListUser.clearFocus()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "minimal 3 karakter",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return true

                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })
        }

    }


    private fun setUserRecyclerView(user: List<UserItem>) {
        binding.rvListUser.apply {
            if (requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                layoutManager = GridLayoutManager(requireContext(), 2)
            } else {
                layoutManager = LinearLayoutManager(requireContext())
            }

            userAdapter = ListUserAdapter(user)
            adapter = userAdapter

            userAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: UserItem) {
                    val actionToDetailUser = HomeFragmentDirections.actionHomeFragmentToDetailUserFragment(
                        data.login
                    )
                    findNavController().navigate(actionToDetailUser)
                }
            })

//            listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
//                override fun onItemClicked(data: UserItem) {
//                    val mDetailUserFragment = DetailUserFragment()
//                    val mBundle = Bundle()
//
//                    mBundle.putParcelable(EXTRA_DATA, data)
//                    mDetailUserFragment.arguments = mBundle
//
//                    val mFragmentManager = parentFragmentManager
//                    mFragmentManager.beginTransaction().apply {
//                        replace(R.id.frame_container, mDetailUserFragment, DetailUserFragment::class.java.simpleName )
//                        addToBackStack(null)
//                        commit()
//                    }
//                }
//            })

        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarHome.visibility = View.VISIBLE
            binding.tvEmptySearch.visibility = View.GONE
        } else {
            binding.progressBarHome.visibility = View.GONE
            binding.rvListUser.visibility = View.VISIBLE
        }
    }

    private fun showEmptyData(isEmpty: Boolean) {
        if (isEmpty) {
            binding.tvNofoundSearch.visibility = View.VISIBLE
            binding.rvListUser.visibility = View.GONE
        } else {
            binding.tvNofoundSearch.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
