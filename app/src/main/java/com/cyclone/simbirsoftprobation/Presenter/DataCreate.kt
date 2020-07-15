package com.cyclone.simbirsoftprobation.Presenter

class DataCreate {
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
}