package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.Presenter.Adapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.profile_fragment.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        val friendsList = mutableListOf(
            Person(
                "Дмитрий Валерьевич",
                LocalDate.of(1990, 5, 5),
                "Стоматолог",
                mutableListOf(),
                R.drawable.avatar_3,
                false
            ),
            Person(
                "Евгений Александров",
                LocalDate.of(1991, 6, 6),
                "Патологоанатом",
                mutableListOf(),
                R.drawable.avatar_2,
                false
            ),
            Person(
                "Виктор Кузнецов",
                LocalDate.of(1992, 7, 7),
                "Терапевт",
                mutableListOf(),
                R.drawable.avatar_1,
                false
            )
        )

        val person = Person(
            "Константинов Денис",
            LocalDate.of(1980, 2, 1),
            "Хирургия, трамвотология", friendsList, R.drawable.image_man, true
        )

        view.avatar_profile.setImageResource(person.iconID)
        view.profile_name.text = person.fullName
        view.birth_day.text = person.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        view.profession.text = person.profession
        view.push.isChecked = person.push

        val recyclerViewFriends = view.recycler_friends
        recyclerViewFriends.layoutManager = LinearLayoutManager(context)
        recyclerViewFriends.adapter = Adapter(friendsList)
        return view
    }
}