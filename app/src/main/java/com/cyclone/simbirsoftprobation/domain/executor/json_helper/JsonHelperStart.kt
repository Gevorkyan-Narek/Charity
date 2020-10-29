package com.cyclone.simbirsoftprobation.domain.executor.json_helper

import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperAsync
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperCallback
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperExecutor
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperIntentService
import com.cyclone.simbirsoftprobation.domain.model.Event

class JsonHelperStart : AppCompatActivity(), JsonHelperCallback<MutableList<Event>> {

    companion object {
        private val instance = JsonHelperStart()

        fun getInstance() = instance
    }

    fun startAsync() {
        JsonHelperAsync(applicationContext, this)
    }

    fun startExecutor() {
        JsonHelperExecutor().submit(applicationContext, this)
    }

    fun startIntentService() {
        JsonHelperIntentService().start(applicationContext)
    }

    override fun onFailure(e: Exception) {
        e.printStackTrace()
    }
}