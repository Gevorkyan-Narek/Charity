package com.cyclone.simbirsoftprobation.presentation.ui.news

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.domain.utilities.MyUtils
import com.cyclone.simbirsoftprobation.domain.utilities.loadDrawable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.news_detail.*


class DetailActivity : AppCompatActivity(R.layout.news_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventId = intent.extras?.getString("event_id")!!

        EventsDataRepository.getInstance().getEvent(eventId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { event ->
                if (event != null) {
                    toolbar_title.text = event.name
                    event_title.text = event.name
                    event_date.text = MyUtils.getRelevance(event)
                    company.text = event.organisation
                    address.text = event.address
                    phone.text = event.phone
                    card_image_1.loadDrawable(this, event.photos[0])
                    card_image_2.loadDrawable(this, event.photos[1])
                    card_image_3.loadDrawable(this, event.photos[2])
                    descr.text = event.description
                }
            }
            .subscribe()

        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Поделиться")
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Поделиться"))
        }
        back.setOnClickListener { finish() }
    }

}