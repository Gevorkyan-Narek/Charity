package com.cyclone.simbirsoftprobation.presentation.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.data.storage.Storage
import com.cyclone.simbirsoftprobation.databinding.ProfileFragmentBinding
import com.cyclone.simbirsoftprobation.domain.model.Person
import com.cyclone.simbirsoftprobation.domain.utilities.loadBitmap
import com.cyclone.simbirsoftprobation.presentation.presenter.FilePresenter
import com.cyclone.simbirsoftprobation.presentation.presenter.ProfilePresenter
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationFragment
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import org.threeten.bp.format.DateTimeFormatter

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        profilePresenter.setProfile(Storage.getInstance().person)
        binding.avatarProfile.setOnClickListener { showPhotoDialogFragment() }
        binding.signOut.setOnClickListener { profilePresenter.signOut() }
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
        binding.avatarProfile.loadBitmap(context!!, person.icon, R.drawable.user_icon)
        binding.profileName.text = person.fullName
        binding.birthDay.text = person.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        binding.profession.text = person.profession
        binding.push.isChecked = person.isPush
    }

    override fun getFriends() {
        binding.recyclerFriends.adapter = FriendsAdapter(Storage.getInstance().friendsList)
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
        binding.avatarProfile.loadBitmap(context!!, profilePresenter.person.icon, R.drawable.user_icon)
    }

    override fun signOut() {
        (activity as MainActivity).mainPresenter.switchToAuth()
    }
}