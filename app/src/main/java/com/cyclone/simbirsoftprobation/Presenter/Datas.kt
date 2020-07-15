package com.cyclone.simbirsoftprobation.Presenter

import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.R
import java.time.LocalDate

class Datas {

    companion object {
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
            "Хирургия, трамвотология", Datas.friendsList, R.drawable.image_man, true
        )
    }
}