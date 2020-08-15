package com.cyclone.simbirsoftprobation.utilities

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.model.Filter
import com.cyclone.simbirsoftprobation.network.FirebaseService
import com.cyclone.simbirsoftprobation.network.RetrofitInstance
import com.cyclone.simbirsoftprobation.storage.Datas
import com.cyclone.simbirsoftprobation.storage.Datas.Companion.checkOfRelevance
import com.cyclone.simbirsoftprobation.storage.Datas.Companion.remainingRelevance
import com.google.gson.GsonBuilder
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers

class MyUtils {
    companion object {
        fun filterNews(events: Event): Boolean {
            val filters = Datas.filter.filter { filter -> filter.check }
            for (filter: Filter in filters) {
                if (events.category == filter.id) return true
            }
            return false
        }

        fun getRelevance(events: Event): String {
            return if (checkOfRelevance(events.endDate)) {
                "Осталось ${remainingRelevance(events.endDate)} дней " +
                        "(${LocalDate.ofEpochDay(events.startDate).format(
                            DateTimeFormatter.ofPattern(
                                "dd.MM"
                            )
                        )} - " +
                        "${LocalDate.ofEpochDay(events.endDate)
                            .format(DateTimeFormatter.ofPattern("dd.MM"))})"
            } else {
                "${Datas.months[LocalDate.ofEpochDay(events.endDate).monthValue - 1]} " +
                        LocalDate.ofEpochDay(events.createAt)
                            .format(DateTimeFormatter.ofPattern("dd, yyyy"))
            }
        }
    }
}

fun ImageView.loadBitmap(
    context: Context,
    bitmap: Bitmap?,
    placeholderDrawable: Int? = null
) {
    if (placeholderDrawable != null)
        Glide.with(context)
            .load(bitmap)
            .placeholder(resources.getDrawable(placeholderDrawable, context.theme))
            .into(this)
    else
        Glide.with(context)
            .load(bitmap)
            .into(this)
}

fun ImageView.loadDrawable(
    context: Context,
    image: String,
    placeholderDrawable: Int? = null
) {

    val drawable = image.getDrawable(context)
    if (placeholderDrawable != null)
        Glide.with(context)
            .load(drawable)
            .placeholder(resources.getDrawable(placeholderDrawable, context.theme))
            .into(this)
    else
        Glide.with(context)
            .load(drawable)
            .into(this)
}

fun String.getDrawable(context: Context): Int {
    return context.resources.getIdentifier(this, "drawable", context.applicationContext.packageName)
}

fun getFilteredEvents(events: List<Event>): MutableList<Event> {
    return if (Datas.filter.all { filter -> !filter.check }) events.toMutableList()
    else events.filter { event ->
        MyUtils.filterNews(event)
    }.toMutableList()
}

fun Retrofit.Builder.connectFirebase(): FirebaseService {
    return baseUrl(RetrofitInstance.FIREBASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(FirebaseService::class.java)
}