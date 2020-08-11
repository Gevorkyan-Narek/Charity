package com.cyclone.simbirsoftprobation.domain.model

data class Filter (
    var id: String,
    var name: String,
    var check: Boolean = true
)