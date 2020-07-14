package com.cyclone.simbirsoftprobation.Presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.item_friend.view.*

class Adapter(private var friends: MutableList<Person>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarFriend = itemView.avatar_friend
        var nameFriend = itemView.name_friend
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameFriend.text = friends[position].fullName
//        holder.avatarFriend.setImageBitmap(friends[position].iconUri)
        Glide.with(holder.itemView).load(friends[position].iconUri).placeholder(R.drawable.user_icon).into(holder.avatarFriend)
    }
}