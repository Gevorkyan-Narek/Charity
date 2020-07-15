package com.cyclone.simbirsoftprobation.Presenter

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.R
import java.time.LocalDate

class Datas(resources: Resources) {

    companion object {

        val fullResultList = mutableListOf(
            "Благотворительный фонд Алины",
            "«Во имя жизни»",
            "Благотворительный фонд В. Потанина",
            "«Детские домики»",
            "«Мозаика счастья»",
            "Благотворительный фонд Алины и Потанины"
        )

        fun getResults(): MutableList<String> {
            val results = mutableListOf<String>()
            repeat(5) { results.add(fullResultList.random()) }
            return results
        }
    }

    val friendsList = mutableListOf(
        Person(
            2,
            "Дмитрий Валерьевич",
            LocalDate.of(1990, 5, 5),
            "Стоматолог",
            mutableListOf(),
            BitmapFactory.decodeResource(resources, R.drawable.avatar_3),
            false
        ),
        Person(
            3,
            "Евгений Александров",
            LocalDate.of(1991, 6, 6),
            "Патологоанатом",
            mutableListOf(),
            BitmapFactory.decodeResource(resources, R.drawable.avatar_2),
            false
        ),
        Person(
            4,
            "Виктор Кузнецов",
            LocalDate.of(1992, 7, 7),
            "Терапевт",
            mutableListOf(),
            BitmapFactory.decodeResource(resources, R.drawable.avatar_1),
            false
        )
    )

    var person: Person = Person(
        1,
        "Константинов Денис",
        LocalDate.of(1980, 2, 1),
        "Хирургия, трамвотология",
        friendsList,
        BitmapFactory.decodeResource(resources, R.drawable.image_man),
        true
    )
}