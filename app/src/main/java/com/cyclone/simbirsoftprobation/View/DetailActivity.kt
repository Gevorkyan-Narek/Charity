package com.cyclone.simbirsoftprobation.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.Presenter.DiffUtils
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.news_detail.*


class DetailActivity : AppCompatActivity(R.layout.news_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventId = intent.extras?.getInt("event_id")!!
        val event = Datas.getInstance().events[eventId]

        toolbar_title.text = event.title
        event_title.text = event.title
        event_date.text = DiffUtils.getRelevance(event)
        company.text = event.company
        address.text = event.address
        phone.text = event.tel
        Glide.with(this).load(event.images[0]).into(card_image_1)
        Glide.with(this).load(event.images[1]).into(card_image_2)
        Glide.with(this).load(event.images[2]).into(card_image_3)
        descr.text = event.fullDescription
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