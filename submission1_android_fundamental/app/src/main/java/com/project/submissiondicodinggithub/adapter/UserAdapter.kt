package com.project.submissiondicodinggithub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.submissiondicodinggithub.DetailActivity
import com.project.submissiondicodinggithub.R
import com.project.submissiondicodinggithub.model.User

class UserAdapter(private var listUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(55, 55))
            .into(holder.avaUser)

        holder.tvName.text = user.name
        holder.tvUsername.text = user.username
        holder.tvCompany.text = user.company

        holder.parentLayout.setOnClickListener {
            it.context.startActivity(
                Intent(it.context, DetailActivity::class.java).putExtra(DetailActivity.EXTRA_DATA_USER, user)
            )
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        var tvCompany: TextView = itemView.findViewById(R.id.tvCompany)
        var avaUser: ImageView = itemView.findViewById(R.id.avaUser)
        var parentLayout: LinearLayout = itemView.findViewById(R.id.parentLayout)
    }

    fun updateList(list: ArrayList<User>) {
        listUser = list
        notifyDataSetChanged()
    }
}