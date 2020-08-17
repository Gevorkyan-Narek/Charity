package com.cyclone.simbirsoftprobation.data.storage

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.io.File
import java.io.IOException

class WorkWithFiles : AppCompatActivity() {

    companion object {
        private val instance = WorkWithFiles()

        fun getInstance(): WorkWithFiles = instance
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(storageDir: File): File {
        val timeStamp: String =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").withZone(ZoneOffset.UTC).format(
                Instant.now()
            )
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    fun fileCreate(storageDir: File): Uri {
        val photoFile: File? = try {
            createImageFile(storageDir)
        } catch (ex: IOException) {
            null
        }

        return FileProvider.getUriForFile(
            this,
            "com.simbirsoftprobation.fileprovider",
            photoFile!!
        )
    }

    fun getBitmap(currentPhotoPath: Uri, contentResolver: ContentResolver): Bitmap {
        val inputStream = contentResolver.openInputStream(currentPhotoPath)
        return BitmapFactory.decodeStream(inputStream)
    }

    fun getBitmap(currentPhotoPath: String): Bitmap {
        BitmapFactory.decodeFile(currentPhotoPath).also { bitmap ->
            val matrix = Matrix()
            val orientation = ExifInterface(currentPhotoPath).getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            matrix.postRotate(fixOrientation(orientation))
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }
    }

    private fun fixOrientation(orientation: Int): Float {
        return when (orientation) {
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
    }
}