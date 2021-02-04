package com.cyclone.simbirsoftprobation.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.model.Person
import com.cyclone.simbirsoftprobation.utilities.loadBitmap
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendsAdapter(private var friends: MutableList<Person>) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarFriend: ImageView = itemView.avatar_friend
        var nameFriend: TextView = itemView.name_friend
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameFriend.text = friends[position].fullName
        holder.avatarFriend.loadBitmap(holder.itemView.context, friends[position].icon, R.drawable.user_icon)
    }
}