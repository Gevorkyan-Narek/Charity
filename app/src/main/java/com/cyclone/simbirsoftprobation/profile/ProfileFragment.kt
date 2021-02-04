package com.cyclone.simbirsoftprobation.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.storage.Datas
import com.cyclone.simbirsoftprobation.utilities.loadBitmap
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*
import org.threeten.bp.format.DateTimeFormatter

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val person = Datas.getInstance().person

        view.avatar_profile.loadBitmap(context!!, person.icon, R.drawable.user_icon)

        view.avatar_profile.setOnClickListener { v ->
            val photoDialogFragment =
                PhotoDialogFragment()
            photoDialogFragment.setTargetFragment(this,
                PhotoDialogFragment.PICK_PHOTO
            )
            photoDialogFragment.show(fragmentManager!!, "photoPicker")
        }
        view.profile_name.text = person.fullName
        view.birth_day.text =
            person.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        view.profession.text = person.profession
        view.push.isChecked = person.isPush

        view.recycler_friends.layoutManager = LinearLayoutManager(context)
        view.recycler_friends.adapter =
            FriendsAdapter(Datas.getInstance().friendsList)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val person = Datas.getInstance().person
        when (resultCode) {
            PhotoDialogFragment.PICK_PHOTO -> {
                val mCurrentPhotoPath = data?.extras?.get("photo") as Uri
                val inputStream = context?.contentResolver?.openInputStream(mCurrentPhotoPath)
                person.icon = BitmapFactory.decodeStream(inputStream)
            }
            PhotoDialogFragment.CREATE_PHOTO -> {
                val currentPhotoPath = data?.getStringExtra("path")

                BitmapFactory.decodeFile(currentPhotoPath).also { bitmap ->
                    val matrix = Matrix()
                    val orientation = ExifInterface(currentPhotoPath!!).getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL
                    )
                    matrix.postRotate(
                        when (orientation) {
                            ExifInterface.ORIENTATION_ROTATE_90 -> {
                                90f
                            }
                            ExifInterface.ORIENTATION_ROTATE_180 -> {
                                180f
                            }
                            ExifInterface.ORIENTATION_ROTATE_270 -> {
                                270f
                            }
                            else -> {
                                0f
                            }
                        }
                    )
                    person.icon = Bitmap.createBitmap(
                        bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
                    )
                }
            }
            PhotoDialogFragment.DELETE_PHOTO -> {
                person.icon = null
            }
        }
        avatar_profile.loadBitmap(context!!, person.icon, R.drawable.user_icon)
    }
}