package com.project.submissiondicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.project.submissiondicoding.fragment.HomeFragment
import com.project.submissiondicoding.fragment.InfoFragment
import com.project.submissiondicoding.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (savedInstanceState == null) {
            btmNavbar.selectedItemId = R.id.home
            addFragment(HomeFragment())
        }

        btmNavbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    addFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.user -> {
                    addFragment(UserFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.info -> {
                    addFragment(InfoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerFrame, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }
}