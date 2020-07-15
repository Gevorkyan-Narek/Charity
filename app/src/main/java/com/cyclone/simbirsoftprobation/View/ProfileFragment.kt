package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.Presenter.Adapter
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.profile_fragment.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment(R.layout.profile_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!

        view.avatar_profile.setImageResource(Datas.person.iconID)
        view.profile_name.text = Datas.person.fullName
        view.birth_day.text = Datas.person.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        view.profession.text = Datas.person.profession
        view.push.isChecked = Datas.person.push

        view.recycler_friends.layoutManager = LinearLayoutManager(context)
        view.recycler_friends.adapter = Adapter(Datas.friendsList)
        return view
    }
}