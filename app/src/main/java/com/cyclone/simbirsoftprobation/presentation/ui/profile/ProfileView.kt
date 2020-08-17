package com.cyclone.simbirsoftprobation.presentation.ui.profile

import android.content.Intent
import com.arellomobile.mvp.MvpView
import com.cyclone.simbirsoftprobation.domain.model.Person

interface ProfileView : MvpView {

    fun showPhotoDialogFragment()
    fun setProfile(person: Person)
    fun getFriends()
    fun getPhotoPickResult(resultCode: Int, data: Intent?)
}