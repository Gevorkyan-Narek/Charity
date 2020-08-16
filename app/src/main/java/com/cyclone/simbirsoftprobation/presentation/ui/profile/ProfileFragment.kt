package com.cyclone.simbirsoftprobation.presentation.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.model.Person
import com.cyclone.simbirsoftprobation.domain.utilities.loadBitmap
import com.cyclone.simbirsoftprobation.presentation.presenter.ProfilePresenter
import com.cyclone.simbirsoftprobation.presentation.presenter.FilePresenter
import com.cyclone.simbirsoftprobation.storage.Datas
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*
import org.threeten.bp.format.DateTimeFormatter

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profilePresenter.setProfile(Datas.getInstance().person)

        view.avatar_profile.setOnClickListener { showPhotoDialogFragment() }
        view.recycler_friends.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        profilePresenter.getPhotoPickResult(resultCode, data)
    }

    override fun showPhotoDialogFragment() {
        val photoDialogFragment = PhotoDialogFragment()
        photoDialogFragment.setTargetFragment(this, PhotoDialogFragment.PICK_PHOTO)
        photoDialogFragment.show(fragmentManager!!, "photoPicker")
    }

    override fun setProfile(person: Person) {
        avatar_profile.loadBitmap(context!!, person.icon, R.drawable.user_icon)
        profile_name.text = person.fullName
        birth_day.text = person.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        profession.text = person.profession
        push.isChecked = person.isPush
    }

    override fun getFriends() {
        recycler_friends.adapter = FriendsAdapter(Datas.getInstance().friendsList)
    }

    override fun getPhotoPickResult(resultCode: Int, data: Intent?) {
        when (resultCode) {
            PhotoDialogFragment.PICK_PHOTO -> {
                val mCurrentPhotoPath = data?.extras?.get("photo") as Uri
                profilePresenter.person.icon =
                    FilePresenter.getBitmap(mCurrentPhotoPath, activity?.contentResolver!!)
            }
            PhotoDialogFragment.CREATE_PHOTO -> {
                val currentPhotoPath = data?.getStringExtra("path")

                if (currentPhotoPath != null)
                    profilePresenter.person.icon = FilePresenter.getBitmap(currentPhotoPath)
            }
            PhotoDialogFragment.DELETE_PHOTO -> {
                profilePresenter.person.icon = null
            }
        }
        avatar_profile.loadBitmap(context!!, profilePresenter.person.icon, R.drawable.user_icon)
    }
}