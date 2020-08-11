package com.cyclone.simbirsoftprobation.storage

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.model.Filter
import com.cyclone.simbirsoftprobation.domain.model.Person
import org.threeten.bp.LocalDate

class Datas(resources: Resources) {

    companion object {
        private lateinit var instance: Datas
        fun newInstance(resources: Resources) {
            instance = Datas(resources)
        }

        fun getInstance(): Datas = instance

        fun checkOfRelevance(dateEnd: Long): Boolean = LocalDate.ofEpochDay(dateEnd).isAfter(LocalDate.now())
        fun remainingRelevance(dateEnd: Long): Int = LocalDate.ofEpochDay(dateEnd).dayOfYear - LocalDate.now().dayOfYear

        private val searchResultExamples = mutableListOf(
            "Благотворительный фонд Алины",
            "«Во имя жизни»",
            "Благотворительный фонд В. Потанина",
            "«Детские домики»",
            "«Мозаика счастья»",
            "Благотворительный фонд Алины и Потанины"
        )

        fun getSearchResultExamples(): MutableList<String> {
            val results = mutableListOf<String>()
            repeat(5) { results.add(searchResultExamples.random()) }
            return results
        }

        var searchResults = mutableListOf<String>()

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

        val categoriesOfHelp = mutableListOf(
            CategoryOfHelp(
                "0",
                "Children",
                "Дети",
                "children"
            ),
            CategoryOfHelp(
                "1",
                "Adults",
                "Взрослые",
                "man"
            ),
            CategoryOfHelp(
                "2",
                "Aged",
                "Пожилые",
                "grand"
            ),
            CategoryOfHelp(
                "3",
                "Animals",
                "Животные",
                "animals"
            ),
            CategoryOfHelp(
                "4",
                "Events",
                "Мероприятия",
                "events"
            )
        )

        var events: MutableList<Event> = mutableListOf()
        var filter = mutableListOf<Filter>()
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

    var person: Person =
        Person(
            1,
            "Константинов Денис",
            LocalDate.of(1980, 2, 1),
            "Хирургия, трамвотология",
            friendsList,
            BitmapFactory.decodeResource(resources, R.drawable.image_man),
            true
        )
}