package com.project.submissiondicodinggithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.submissiondicodinggithub.model.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_USER = "extra_data_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val user = intent.getParcelableExtra<User>(EXTRA_DATA_USER) as User

        tvRepo.text = user.repository
        tvFollowers.text = user.followers
        tvFollowing.text = user.following
        tvName.text = user.name
        tvUsername.text = user.username
        tvCompany.text = user.company
        tvLocation.text = user.location

        user.avatar?.let { avaUser.setImageResource(it) }
    }
}