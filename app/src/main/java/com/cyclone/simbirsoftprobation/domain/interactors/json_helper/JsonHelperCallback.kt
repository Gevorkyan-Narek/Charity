package com.cyclone.simbirsoftprobation.domain.interactors.json_helper

interface JsonHelperCallback<T> {
    fun onFailure(e: Exception)
}