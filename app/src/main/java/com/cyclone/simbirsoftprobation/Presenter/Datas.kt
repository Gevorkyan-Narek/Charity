package com.cyclone.simbirsoftprobation.Presenter

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.cyclone.simbirsoftprobation.Model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.Model.Event
import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.R
import org.threeten.bp.LocalDate

class Datas(resources: Resources) {

    companion object {

        private val fullResultList = mutableListOf(
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

        val months = listOf("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь")
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

    val helps = mutableListOf(
        CategoryOfHelp("Дети", BitmapFactory.decodeResource(resources, R.drawable.children)),
        CategoryOfHelp("Взрослые", BitmapFactory.decodeResource(resources, R.drawable.man)),
        CategoryOfHelp("Пожилые", BitmapFactory.decodeResource(resources, R.drawable.grand)),
        CategoryOfHelp("Животные", BitmapFactory.decodeResource(resources, R.drawable.animals)),
        CategoryOfHelp("Мероприятия", BitmapFactory.decodeResource(resources, R.drawable.events))
    )

    val events = mutableListOf(
        Event(
            1,
            "Спонсоры отремонтируют школу-интернат",
            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области...",
            LocalDate.of(2020, 9, 21),
            LocalDate.of(2020, 10, 20),
            BitmapFactory.decodeResource(resources, R.drawable.event_1),
            mutableListOf(helps[0], helps[1], helps[2], helps[3])
        ),
        Event(
            1,
            "Конкурс по вокальному пению в детском доме №6",
            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
            LocalDate.of(2016, 9, 20),
            LocalDate.of(2016, 10, 20),
            BitmapFactory.decodeResource(resources, R.drawable.event_2),
            mutableListOf(helps[1], helps[2], helps[3], helps[4])
        )
    )
}