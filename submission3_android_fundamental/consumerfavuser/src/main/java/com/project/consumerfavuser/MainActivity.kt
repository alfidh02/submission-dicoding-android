package com.project.consumerfavuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.consumerfavuser.adapter.UserAdapter
import com.project.consumerfavuser.databinding.ActivityMainBinding
import com.project.consumerfavuser.viewModel.FavoriteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.apply {
            rvFavorite.setHasFixedSize(true)
            rvFavorite.layoutManager = LinearLayoutManager(this@MainActivity)
            rvFavorite.adapter = userAdapter
        }


        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        favoriteViewModel.setListFavUser(this)
        favoriteViewModel.getListFavUser().observe(this, {
            if (it != null) {
                userAdapter.setList(it)
            }
        })
    }
}