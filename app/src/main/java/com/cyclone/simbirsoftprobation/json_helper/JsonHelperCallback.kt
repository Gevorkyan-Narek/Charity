package com.cyclone.simbirsoftprobation.json_helper

interface JsonHelperCallback<T> {
    fun onSuccess(result: T)
    fun onFailure(e: Exception)
}