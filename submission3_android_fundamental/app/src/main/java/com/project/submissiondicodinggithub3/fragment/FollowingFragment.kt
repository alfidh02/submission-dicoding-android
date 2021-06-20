package com.project.submissiondicodinggithub3.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.submissiondicodinggithub3.DetailActivity
import com.project.submissiondicodinggithub3.R
import com.project.submissiondicodinggithub3.adapter.UserAdapter
import com.project.submissiondicodinggithub3.databinding.FragmentFollowingBinding
import com.project.submissiondicodinggithub3.viewModel.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_following) {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var followingViewModel: FollowingViewModel
    private lateinit var userListAdapter: UserAdapter
    private lateinit var githubUsername: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        githubUsername = arguments?.getString(DetailActivity.USERNAME_CONST).toString()

        _binding = FragmentFollowingBinding.bind(view)

        userListAdapter = UserAdapter()
        userListAdapter.notifyDataSetChanged()

        binding.rvGithubFollowing.setHasFixedSize(true)
        binding.rvGithubFollowing.layoutManager = LinearLayoutManager(activity)
        binding.rvGithubFollowing.adapter = userListAdapter

        showBuffer(true)

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowingViewModel::class.java)
        followingViewModel.setListFollowing(githubUsername)
        followingViewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                userListAdapter.setList(it)
                showBuffer(false)
            }
        })
    }

    private fun showBuffer(state: Boolean) {
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