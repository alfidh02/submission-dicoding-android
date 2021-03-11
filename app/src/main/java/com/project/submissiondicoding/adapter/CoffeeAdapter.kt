package com.project.submissiondicoding.adapter

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
import com.project.submissiondicoding.DetailActivity
import com.project.submissiondicoding.R
import com.project.submissiondicoding.model.Coffee

class CoffeeAdapter(private val listCoffee: ArrayList<Coffee>) :
    RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_coffee, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCoffee.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coffee = listCoffee[position]

        Glide.with(holder.itemView.context)
            .load(coffee.photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvName.text = coffee.name
        holder.tvDetail.text = coffee.detail

        holder.parentLayout.setOnClickListener {
            it.context.startActivity(
                Intent(it.context, DetailActivity::class.java).putExtra(
                    "nama",
                    coffee.name
                ).putExtra("detail", coffee.detail).putExtra("gambar", coffee.photo)
                    .putExtra("lokasi", coffee.location)
            )
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var parentLayout: LinearLayout = itemView.findViewById(R.id.parentLayout)
    }
}