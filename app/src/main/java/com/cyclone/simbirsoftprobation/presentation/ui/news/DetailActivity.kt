package com.cyclone.simbirsoftprobation.presentation.ui.news

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.NewsDetailBinding
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.domain.utilities.MyUtils
import com.cyclone.simbirsoftprobation.domain.utilities.loadDrawable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var eventsDataRepository: EventsDataRepository
    private lateinit var binding: NewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)
        binding = NewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val eventId = intent.extras?.getString("event_id")!!
        eventsDataRepository.getEvent(eventId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { event ->
                binding.toolbarTitle.text = event.name
                binding.eventTitle.text = event.name
                binding.eventDate.text = MyUtils.getRelevance(event)
                binding.company.text = event.organisation
                binding.address.text = event.address
                binding.phone.text = event.phone
                binding.cardImage1.loadDrawable(this, event.photos[0])
                binding.cardImage2.loadDrawable(this, event.photos[1])
                binding.cardImage3.loadDrawable(this, event.photos[2])
                binding.descr.text = event.description
            }
            .subscribe()

        binding.share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Поделиться")
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Поделиться"))
        }
        binding.back.setOnClickListener { finish() }
    }

}