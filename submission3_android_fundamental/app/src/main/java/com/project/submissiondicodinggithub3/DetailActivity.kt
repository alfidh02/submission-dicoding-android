package com.project.submissiondicodinggithub3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.submissiondicodinggithub3.adapter.PagerAdapter
import com.project.submissiondicodinggithub3.databinding.ActivityDetailBinding
import com.project.submissiondicodinggithub3.viewModel.UserDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    companion object {
        const val USERNAME_CONST = "username"
        const val ID_CONST = "id"
        const val AVA_CONST = "avatar"
        const val TYPE_CONST = "typeuser"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gitUsername = intent.getStringExtra(USERNAME_CONST)
        val gitId = intent.getIntExtra(ID_CONST, 0)
        val gitAvatar = intent.getStringExtra(AVA_CONST)
        val gitType = intent.getStringExtra(TYPE_CONST)

        val saveData = Bundle()
        saveData.putString(USERNAME_CONST, gitUsername)

        detailViewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
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

        var checkUserFav = false
        CoroutineScope(Dispatchers.IO).launch {
            val countFav = detailViewModel.checkUserFavList(gitId)
            withContext(Dispatchers.Main) {
                if (countFav != null) {
                    if (countFav > 0) {
                        val buttonFav = binding.tbFavorite
                        if (buttonFav != null) buttonFav.isChecked = true
                        checkUserFav = true
                    } else {
                        val buttonFav = binding.tbFavorite
                        if (buttonFav != null) buttonFav.isChecked = false
                        checkUserFav = false
                    }
                }
            }
        }

        binding.tbFavorite?.setOnClickListener {
            checkUserFav = !checkUserFav
            if (checkUserFav) {
                detailViewModel.addToFavList(
                    gitUsername.toString(),
                    gitId,
                    gitAvatar.toString(),
                    gitType.toString()
                )
                Toast.makeText(
                    applicationContext,
                    getString(R.string.add_favorite), Toast.LENGTH_SHORT
                ).show()
            } else {
                detailViewModel.removeFromFavList(gitId)
                Toast.makeText(
                    applicationContext,
                    getString(R.string.remove_favorite), Toast.LENGTH_SHORT
                ).show()
                val buttonFav = binding.tbFavorite
                if (buttonFav != null) buttonFav.isChecked = checkUserFav
            }
        }
    }

}
