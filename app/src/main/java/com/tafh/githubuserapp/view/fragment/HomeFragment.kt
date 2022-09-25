package com.tafh.githubuserapp.view.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.data.Utils
import com.tafh.githubuserapp.databinding.FragmentHomeBinding
import com.tafh.githubuserapp.view.activity.MainActivity


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var actionBar: ActionBar
//    private var userList = listOf<User>()


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

        actionBar = (activity as MainActivity).supportActionBar!!
        actionBar.apply {
            title = getString(R.string.title_list_user_fragment)
        }

        getLocalData()

        binding.rvListUser.setHasFixedSize(true)
        listUserRecyclerView()
    }

    private fun getLocalData() {
        val jsonfile = "githubuser.json"
        val jsonString = Utils().getJsonFromAssets(requireContext(), jsonfile)

        val gson = Gson()
//        userList = gson.fromJson(jsonString, UserList::class.java).users
    }

    private fun listUserRecyclerView() {
        binding.rvListUser.apply {
            if (requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                layoutManager = GridLayoutManager(requireContext(), 2)
            } else {
                layoutManager = LinearLayoutManager(requireContext())
            }

//            val listUserAdapter = ListUserAdapter(userList)
//            adapter = listUserAdapter

//            listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
//                override fun onItemClicked(data: User) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}