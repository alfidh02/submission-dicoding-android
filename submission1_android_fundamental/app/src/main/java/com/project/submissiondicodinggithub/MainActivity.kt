package com.project.submissiondicodinggithub

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.submissiondicodinggithub.adapter.UserAdapter
import com.project.submissiondicodinggithub.data.UserData
import com.project.submissiondicodinggithub.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parentView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                etSearch.clearFocus()
                hideKeyboard()
            }
            true
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ivClear.visibility = View.VISIBLE
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        ivClear.setOnClickListener {
            etSearch.setText("")
            ivClear.visibility = View.GONE
        }

        rvUser.setHasFixedSize(true)
        list.addAll(UserData.listData)
        showUserList()
    }

    private fun showUserList() {
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = UserAdapter(list)
        rvUser.adapter = listUserAdapter
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }

    private fun filter(text: String) {
        val temp = ArrayList<User>()
        for (l in list) {
            if (l.name!!.toLowerCase().contains(text.toLowerCase())) {
                temp.add(l)
            }
        }
        (rvUser.adapter as UserAdapter).updateList(temp)
    }
}