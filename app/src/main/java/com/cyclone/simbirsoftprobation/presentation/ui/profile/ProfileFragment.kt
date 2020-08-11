package com.cyclone.simbirsoftprobation.presentation.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.utilities.loadBitmap
import com.cyclone.simbirsoftprobation.presentation.presenter.profile.FilePresenter
import com.cyclone.simbirsoftprobation.storage.Datas
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*
import org.threeten.bp.format.DateTimeFormatter

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val person = Datas.getInstance().person

        view.avatar_profile.loadBitmap(context!!, person.icon, R.drawable.user_icon)

        view.avatar_profile.setOnClickListener {
            val photoDialogFragment = PhotoDialogFragment()
            photoDialogFragment.setTargetFragment(this, PhotoDialogFragment.PICK_PHOTO)
            photoDialogFragment.show(fragmentManager!!, "photoPicker")
        }
        view.profile_name.text = person.fullName
        view.birth_day.text = person.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        view.profession.text = person.profession
        view.push.isChecked = person.isPush

        view.recycler_friends.layoutManager = LinearLayoutManager(context)
        view.recycler_friends.adapter = FriendsAdapter(Datas.getInstance().friendsList)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val person = Datas.getInstance().person
        when (resultCode) {
            PhotoDialogFragment.PICK_PHOTO -> {
                val mCurrentPhotoPath = data?.extras?.get("photo") as Uri
                person.icon =
                    FilePresenter.getBitmap(mCurrentPhotoPath, activity?.contentResolver!!)
            }
            PhotoDialogFragment.CREATE_PHOTO -> {
                val currentPhotoPath = data?.getStringExtra("path")

                if (currentPhotoPath != null)
                    person.icon = FilePresenter.getBitmap(currentPhotoPath)
            }
            PhotoDialogFragment.DELETE_PHOTO -> {
                person.icon = null
            }
        }
        avatar_profile.loadBitmap(context!!, person.icon, R.drawable.user_icon)
    }
}