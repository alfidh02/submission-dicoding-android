package com.project.submissiondicodinggithub2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.submissiondicodinggithub2.databinding.ActivityDetailBinding
import com.project.submissiondicodinggithub2.viewModel.UserDetailViewModel
import com.project.submissiondicodinggithub2.adapter.PagerAdapter

class DetailActivity : AppCompatActivity() {
    companion object {
        const val USERNAME_CONST = "username"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: UserDetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gitUsername = intent.getStringExtra(USERNAME_CONST)
        val saveData = Bundle()
        saveData.putString(USERNAME_CONST, gitUsername)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(UserDetailViewModel::class.java)
        detailViewModel.setUserDetail(gitUsername.toString())
        detailViewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = it.followers.toString()
                    tvFollowing.text = it.following.toString()
                    tvRepo.text = it.public_repos.toString()
                    tvCompany.text = it.company
                    tvLocation.text = it.location
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .into(avaUser)
                }
            }
        })

        val pagerAdapter = PagerAdapter(this, supportFragmentManager, saveData)

            binding.viewPager.adapter = pagerAdapter
            binding.tabs.setupWithViewPager(binding.viewPager)
    }

}
