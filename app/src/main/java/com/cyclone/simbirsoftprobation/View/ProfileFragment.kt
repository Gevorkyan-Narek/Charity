package com.cyclone.simbirsoftprobation.View

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Model.Person
import com.cyclone.simbirsoftprobation.Presenter.Adapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment() {

    lateinit var person: Person

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        val friendsList = mutableListOf(
            Person(
                2,
                "Дмитрий Валерьевич",
                LocalDate.of(1990, 5, 5),
                "Стоматолог",
                mutableListOf(),
                BitmapFactory.decodeResource(resources, R.drawable.avatar_3),
                false
            ),
            Person(
                3,
                "Евгений Александров",
                LocalDate.of(1991, 6, 6),
                "Патологоанатом",
                mutableListOf(),
                BitmapFactory.decodeResource(resources, R.drawable.avatar_2),
                false
            ),
            Person(
                4,
                "Виктор Кузнецов",
                LocalDate.of(1992, 7, 7),
                "Терапевт",
                mutableListOf(),
                BitmapFactory.decodeResource(resources, R.drawable.avatar_1),
                false
            )
        )

        person = Person(
            1,
            "Константинов Денис",
            LocalDate.of(1980, 2, 1),
            "Хирургия, трамвотология",
            friendsList,
            BitmapFactory.decodeResource(resources, R.drawable.image_man),
            true
        )

        Glide.with(context!!).load(person.iconUri).centerInside().into(view.avatar_profile)
        view.avatar_profile.setOnClickListener { v ->
            val photoDialogFragment = PhotoDialogFragment()
            photoDialogFragment.setTargetFragment(this, PhotoDialogFragment.PICK_PHOTO)
            photoDialogFragment.show(fragmentManager!!, "photoPicker")
        }
        view.profile_name.text = person.fullName
        view.birth_day.text = person.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
        view.profession.text = person.profession
        view.push.isChecked = person.push

        val recyclerViewFriends = view.recycler_friends
        recyclerViewFriends.layoutManager = LinearLayoutManager(context)
        recyclerViewFriends.adapter = Adapter(friendsList)
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            PhotoDialogFragment.PICK_PHOTO -> {
                val mCurrentPhotoPath = data?.extras?.get("photo") as Uri
                val inputStream = context?.contentResolver?.openInputStream(mCurrentPhotoPath)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                person.iconUri = bitmap
            }
            PhotoDialogFragment.CREATE_PHOTO -> {
                val mCurrentPhotoPath = data?.getStringExtra("path")

                BitmapFactory.decodeFile(mCurrentPhotoPath).also { bitmap ->
                    val matrix = Matrix()
                    val orientation = ExifInterface(mCurrentPhotoPath!!).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
                    matrix.postRotate(when (orientation) {
                        ExifInterface.ORIENTATION_ROTATE_90 -> {
                            90f
                        }
                        ExifInterface.ORIENTATION_ROTATE_180 -> {
                            180f
                        }
                        ExifInterface.ORIENTATION_ROTATE_270 -> {
                            270f
                        }
                        else -> { 0f }
                    })
                    val rotatedBitmap = Bitmap.createBitmap(
                        bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
                    )
                    person.iconUri = rotatedBitmap
                }
            }
            PhotoDialogFragment.DELETE_PHOTO -> {
                person.iconUri = null
            }
        }
        Glide.with(context!!).load(person.iconUri).centerInside().placeholder(R.drawable.user_icon).into(avatar_profile)
    }
}