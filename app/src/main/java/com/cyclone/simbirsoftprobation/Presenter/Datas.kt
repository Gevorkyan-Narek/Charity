package com.cyclone.simbirsoftprobation.Presenter

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.cyclone.simbirsoftprobation.Model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.Model.Event
import com.cyclone.simbirsoftprobation.Model.Filter
import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.R
import org.threeten.bp.LocalDate

class Datas(resources: Resources) {

    companion object {
        private lateinit var instance: Datas
        fun newInstance(resources: Resources) {
            instance = Datas(resources)
        }

        fun getInstance(): Datas = instance

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

        val months = listOf(
            "Январь",
            "Февраль",
            "Март",
            "Апрель",
            "Май",
            "Июнь",
            "Июль",
            "Август",
            "Сентябрь",
            "Октябрь",
            "Ноябрь",
            "Декабрь"
        )

        var events: MutableList<Event> = mutableListOf()
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

    val categoriesOfHelp = mutableListOf(
        CategoryOfHelp("Дети", BitmapFactory.decodeResource(resources, R.drawable.children)),
        CategoryOfHelp("Взрослые", BitmapFactory.decodeResource(resources, R.drawable.man)),
        CategoryOfHelp("Пожилые", BitmapFactory.decodeResource(resources, R.drawable.grand)),
        CategoryOfHelp("Животные", BitmapFactory.decodeResource(resources, R.drawable.animals)),
        CategoryOfHelp("Мероприятия", BitmapFactory.decodeResource(resources, R.drawable.events))
    )

    val filter = categoriesOfHelp.map { Filter(it.name) }.toMutableList()



//    val events = mutableListOf(
//        Event(
//            0,
//            "Спонсоры отремонтируют школу-интернат",
//            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области...",
//            LocalDate.of(2020, 9, 21),
//            LocalDate.of(2020, 10, 20),
//            R.drawable.event_1,
//            mutableListOf(categoriesOfHelp[0].name, categoriesOfHelp[1].name, categoriesOfHelp[2].name),
//            "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.\n" +
//                    "При этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
//            mutableListOf(
//                R.drawable.cardimage_1,
//                R.drawable.cardimage_2,
//                R.drawable.cardimage_3
//            ),
//            "Санкт-Петербург, Кирочная улица,д. 50А, каб. 208",
//            "+7 (937) 037 37-73\n+7 (937) 016 16-16",
//            "Благотворительный Фонд «Счастливый Мир»"
//        ),
//        Event(
//            1,
//            "Конкурс по вокальному пению в детском доме №6",
//            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
//            LocalDate.of(2016, 9, 20),
//            LocalDate.of(2016, 10, 20),
//            R.drawable.event_2,
//            mutableListOf(categoriesOfHelp[2].name, categoriesOfHelp[3].name, categoriesOfHelp[4].name),
//            "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.\n" +
//                    "При этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
//            mutableListOf(
//                R.drawable.cardimage_1,
//                R.drawable.cardimage_2,
//                R.drawable.cardimage_3
//            ),
//            "Санкт-Петербург, Кирочная улица,д. 50А, каб. 208",
//            "+7 (937) 037 37-73\n+7 (937) 016 16-16",
//            "Благотворительный Фонд «Счастливый Мир»"
//        )
//    )



//    val events = mutableListOf(
//        Event(
//            0,
//            "Спонсоры отремонтируют школу-интернат",
//            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области...",
//            LocalDate.of(2020, 9, 21),
//            LocalDate.of(2020, 10, 20),
//            BitmapFactory.decodeResource(resources, R.drawable.event_1),
//            mutableListOf(categoriesOfHelp[0], categoriesOfHelp[1], categoriesOfHelp[2]),
//            "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.\n" +
//                    "При этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
//            mutableListOf(
//                BitmapFactory.decodeResource(resources, R.drawable.cardimage_1),
//                BitmapFactory.decodeResource(resources, R.drawable.cardimage_2),
//                BitmapFactory.decodeResource(resources, R.drawable.cardimage_3)
//            ),
//            "Санкт-Петербург, Кирочная улица,д. 50А, каб. 208",
//            "+7 (937) 037 37-73\n+7 (937) 016 16-16",
//            "Благотворительный Фонд «Счастливый Мир»"
//        ),
//        Event(
//            1,
//            "Конкурс по вокальному пению в детском доме №6",
//            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
//            LocalDate.of(2016, 9, 20),
//            LocalDate.of(2016, 10, 20),
//            BitmapFactory.decodeResource(resources, R.drawable.event_2),
//            mutableListOf(categoriesOfHelp[2], categoriesOfHelp[3], categoriesOfHelp[4]),
//            "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.\n" +
//                    "При этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
//            mutableListOf(
//                BitmapFactory.decodeResource(resources, R.drawable.cardimage_1),
//                BitmapFactory.decodeResource(resources, R.drawable.cardimage_2),
//                BitmapFactory.decodeResource(resources, R.drawable.cardimage_3)
//            ),
//            "Санкт-Петербург, Кирочная улица,д. 50А, каб. 208",
//            "+7 (937) 037 37-73\n+7 (937) 016 16-16",
//            "Благотворительный Фонд «Счастливый Мир»"
//        )
//    )
}