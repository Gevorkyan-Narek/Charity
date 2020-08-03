package com.cyclone.simbirsoftprobation.json_helper

import com.cyclone.simbirsoftprobation.model.Event
import java.lang.Exception

interface JsonHelperCallback<T> {
    fun onSuccess(result: T)
    fun onFailure(e: Exception)
}