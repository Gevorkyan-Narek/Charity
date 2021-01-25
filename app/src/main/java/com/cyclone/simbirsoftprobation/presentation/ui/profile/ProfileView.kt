package com.cyclone.simbirsoftprobation.presentation.ui.profile

import android.content.Intent
import com.cyclone.simbirsoftprobation.domain.model.Person
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView : MvpView {

    fun showPhotoDialogFragment()
    fun setProfile(person: Person)
    fun getFriends()
    fun getPhotoPickResult(resultCode: Int, data: Intent?)
    fun signOut()
}