package com.project.submissiondicodinggithub3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.submissiondicodinggithub3.adapter.UserAdapter
import com.project.submissiondicodinggithub3.databinding.ActivityFavoriteBinding
import com.project.submissiondicodinggithub3.model.User
import com.project.submissiondicodinggithub3.model.UserFavorite
import com.project.submissiondicodinggithub3.viewModel.FavoriteViewModel
import java.util.ArrayList

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favViewModel: FavoriteViewModel
    private lateinit var favAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favAdapter = UserAdapter()
        favAdapter.notifyDataSetChanged()

        favAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.apply {
                    putExtra(DetailActivity.USERNAME_CONST, data.login)
                    putExtra(DetailActivity.ID_CONST, data.id)
                    putExtra(DetailActivity.AVA_CONST, data.avatar_url)
                    putExtra(DetailActivity.TYPE_CONST, data.type)
                    startActivity(intent)
                }
            }
        })

        favViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        favViewModel.getListFavUser()?.observe(this, {
            if (it != null) {
                val listMap = listMap(it)
                favAdapter.setList(listMap)
            }
        })

        binding.apply {
            rvFavorite.setHasFixedSize(true)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavorite.adapter = favAdapter
        }
    }

    private fun listMap(users: List<UserFavorite>): ArrayList<User> {
        val userList = ArrayList<User>()
        for (user in users) {
            val mapUser = User(user.login, user.id, user.avatar_url, user.type)
            userList.add(mapUser)
        }
        return userList
    }
}