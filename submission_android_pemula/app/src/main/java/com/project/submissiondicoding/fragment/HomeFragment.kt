package com.project.submissiondicoding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.submissiondicoding.R
import com.project.submissiondicoding.adapter.CoffeeAdapter
import com.project.submissiondicoding.data.CoffeeData
import com.project.submissiondicoding.model.Coffee
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private var list: ArrayList<Coffee> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCoffee.setHasFixedSize(true)

        list.addAll(CoffeeData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvCoffee.layoutManager = LinearLayoutManager(context)
        val listCoffeeAdapter = CoffeeAdapter(list)
        rvCoffee.adapter = listCoffeeAdapter
    }


}