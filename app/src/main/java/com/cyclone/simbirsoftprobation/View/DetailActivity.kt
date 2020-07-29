package com.cyclone.simbirsoftprobation.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.Presenter.DiffUtils
import com.cyclone.simbirsoftprobation.Presenter.loadBitmap
import com.cyclone.simbirsoftprobation.Presenter.loadDrawable
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.news_detail.*


class DetailActivity : AppCompatActivity(R.layout.news_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventId = intent.extras?.getInt("event_id")!!
        val event = Datas.events[eventId]
        toolbar_title.text = event.title
        event_title.text = event.title
        event_date.text = DiffUtils.getRelevance(event)
        company.text = event.company
        address.text = event.address
        phone.text = event.tel
        card_image_1.loadDrawable(this, event.images[0])
        card_image_2.loadDrawable(this, event.images[1])
        card_image_3.loadDrawable(this, event.images[2])
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