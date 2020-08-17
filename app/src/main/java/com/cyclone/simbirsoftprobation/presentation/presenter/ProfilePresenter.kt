package com.cyclone.simbirsoftprobation.presentation.presenter

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.domain.model.Person
import com.cyclone.simbirsoftprobation.presentation.ui.profile.ProfileView

@InjectViewState
class ProfilePresenter: MvpPresenter<ProfileView>() {

    lateinit var person: Person

    fun setProfile(person: Person) {
        this.person = person
        viewState.setProfile(person)
        viewState.getFriends()
    }

    fun getPhotoPickResult(resultCode: Int, data: Intent?) {
        viewState.getPhotoPickResult(resultCode, data)
    }
}