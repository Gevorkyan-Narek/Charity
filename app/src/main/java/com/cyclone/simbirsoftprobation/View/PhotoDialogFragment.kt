package com.cyclone.simbirsoftprobation.View

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.photo_dialog.view.*
import kotlinx.android.synthetic.main.profile_fragment.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class PhotoDialogFragment : DialogFragment(), View.OnClickListener {

    companion object {
        val PICK_PHOTO = 1
        val CREATE_PHOTO = 2
        val DELETE_PHOTO = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.photo_dialog, null)
        view.choose_photo.setOnClickListener(this)
        view.create_photo.setOnClickListener(this)
        view.delete_photo.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.choose_photo -> {
                val openGallery = Intent()
                openGallery.type = "image/*"
                openGallery.action = Intent.ACTION_PICK
                startActivityForResult(
                    Intent.createChooser(openGallery, "Выберите фото"),
                    PICK_PHOTO
                )
            }
            R.id.create_photo -> {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    // Ensure that there's a camera activity to handle the intent
                    takePictureIntent.resolveActivity(context?.packageManager!!)?.also {
                        // Create the File where the photo should go
                        val photoFile: File? = try {
                            createImageFile()
                        } catch (ex: IOException) {
                            // Error occurred while creating the File
                            null
                        }
                        // Continue only if the File was successfully created
                        photoFile?.also {
                            val photoURI: Uri = FileProvider.getUriForFile(
                                context!!,
                                "com.example.android.fileprovider",
                                it
                            )
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            startActivityForResult(takePictureIntent, CREATE_PHOTO)
                        }
                    }
                }
            }
            R.id.delete_photo -> {
                targetFragment?.onActivityResult(targetRequestCode, DELETE_PHOTO, Intent())
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
                        val imageUri = data.data!!
                        val intent = Intent().putExtra("photo", imageUri)
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
                        val intent = Intent().putExtra("path", mCurrentPhotoPath)
                        targetFragment?.onActivityResult(targetRequestCode, CREATE_PHOTO, intent)
                        dismiss()
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    lateinit var mCurrentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = absolutePath
        }
    }
}