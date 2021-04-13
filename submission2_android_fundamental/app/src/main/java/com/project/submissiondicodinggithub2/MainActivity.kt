package com.project.submissiondicodinggithub2

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.submissiondicodinggithub2.adapter.UserAdapter
import com.project.submissiondicodinggithub2.databinding.ActivityMainBinding
import com.project.submissiondicodinggithub2.model.User
import com.project.submissiondicodinggithub2.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.adapter = userAdapter

            etSearch.setOnKeyListener { _, key, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && key == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.USERNAME_CONST, data.login)
                startActivity(intent)
            }
        })

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        userViewModel.getUserSearch().observe(this, {
            if (it != null) {
                userAdapter.setList(it)
                showLoading(false)
            }
        })

    }

    private fun searchUser() {
        binding.apply {
            val query = etSearch.text.toString()

            if (query.isEmpty()) Toast.makeText(this@MainActivity, resources.getString(R.string.empty_search), Toast.LENGTH_SHORT).show() else showLoading(true)

            userViewModel.setUserSearch(query)
        }
    }

    private fun showLoading(status: Boolean) {
        if (status) {
            binding.apply {
                rvUser.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
                ivHome.visibility = View.VISIBLE
                tvHome.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvUser.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                ivHome.visibility = View.INVISIBLE
                tvHome.visibility = View.INVISIBLE
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lang, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.switch_lang) {
            val langIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(langIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}