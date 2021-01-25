package com.cyclone.simbirsoftprobation.presentation.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.PhotoDialogBinding
import com.cyclone.simbirsoftprobation.presentation.presenter.FilePresenter
import java.io.FileNotFoundException

class PhotoDialogFragment : DialogFragment(), View.OnClickListener {

    companion object {
        const val PICK_PHOTO = 1
        const val CREATE_PHOTO = 2
        const val DELETE_PHOTO = 3
    }

    private lateinit var binding: PhotoDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhotoDialogBinding.inflate(inflater, container, false)

        binding.choosePhoto.setOnClickListener(this)
        binding.createPhoto.setOnClickListener(this)
        binding.deletePhoto.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.choose_photo -> {
                val openGallery = Intent().setType("image/*").setAction(Intent.ACTION_PICK)
                startActivityForResult(
                    Intent.createChooser(openGallery, "Выберите фото"),
                    PICK_PHOTO
                )
            }
            R.id.create_photo -> {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(context?.packageManager!!)?.also {
                        val photoURI = FilePresenter.create(activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(
                            takePictureIntent,
                            CREATE_PHOTO
                        )
                    }
                }
            }
            R.id.delete_photo -> {
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    DELETE_PHOTO, Intent()
                )
                dismiss()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            PICK_PHOTO -> {
                if (resultCode == RESULT_OK && data != null) {
                    try {
                        val intent = Intent().putExtra("photo", data.data!!)
                        targetFragment?.onActivityResult(targetRequestCode, PICK_PHOTO, intent)
                        dismiss()
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
            CREATE_PHOTO -> {
                if (resultCode == RESULT_OK) {
                    try {
                        val intent = Intent().putExtra("path", FilePresenter.getCurrentPhotoPath())
                        targetFragment?.onActivityResult(
                            targetRequestCode,
                            CREATE_PHOTO, intent
                        )
                        dismiss()
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}