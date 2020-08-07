package com.cyclone.simbirsoftprobation.json_helper

interface JsonHelperCallback<T> {
    fun onFailure(e: Exception)
}