package com.cyclone.simbirsoftprobation.presentation.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.ItemFriendBinding
import com.cyclone.simbirsoftprobation.domain.model.Person
import com.cyclone.simbirsoftprobation.domain.utilities.loadBitmap

class FriendsAdapter(private var friends: MutableList<Person>) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        var avatarFriend: ImageView = binding.avatarFriend
        var nameFriend: TextView = binding.nameFriend
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameFriend.text = friends[position].fullName
        holder.avatarFriend.loadBitmap(
            holder.itemView.context,
            friends[position].icon,
            R.drawable.user_icon
        )
    }
}