package com.tiorisnanto.githubusertio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GithubAdapter(private val users: ArrayList<Users>) : RecyclerView.Adapter<GithubAdapter.MyViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: Users)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvCompany: TextView = itemView.findViewById(R.id.tv_item_company)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (name, username, company, avatar) = users[position]
        holder.imgPhoto.setImageResource(avatar)
        holder.tvName.text = name
        holder.tvUsername.text = username
        holder.tvCompany.text = company
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(users[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}