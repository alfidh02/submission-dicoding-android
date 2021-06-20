package com.project.submissiondicodinggithub3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.submissiondicodinggithub3.DetailActivity
import com.project.submissiondicodinggithub3.R
import com.project.submissiondicodinggithub3.adapter.UserAdapter
import com.project.submissiondicodinggithub3.databinding.FragmentFollowersBinding
import com.project.submissiondicodinggithub3.viewModel.FollowersViewModel

class FollowersFragment : Fragment(R.layout.fragment_followers) {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var userListAdapter: UserAdapter
    private lateinit var gitUsername: String
    private lateinit var followersViewModel: FollowersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gitUsername = arguments?.getString(DetailActivity.USERNAME_CONST).toString()

        _binding = FragmentFollowersBinding.bind(view)

        userListAdapter = UserAdapter()
        userListAdapter.notifyDataSetChanged()

        binding.rvGithubFollowers.setHasFixedSize(true)
        binding.rvGithubFollowers.layoutManager = LinearLayoutManager(activity)
        binding.rvGithubFollowers.adapter = userListAdapter

        showData(true)

        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowersViewModel::class.java)
        followersViewModel.setListFollowers(gitUsername)
        followersViewModel.getListFollowers().observe(viewLifecycleOwner,{
            if (it != null) {
                userListAdapter.setList(it)
                showData(false)
            }
        })
    }

    private fun showData(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}