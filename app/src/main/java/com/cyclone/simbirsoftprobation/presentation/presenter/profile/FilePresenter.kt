package com.cyclone.simbirsoftprobation.presentation.presenter.profile

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import com.cyclone.simbirsoftprobation.data.storage.WorkWithFiles
import java.io.File

class FilePresenter {

    companion object {

        fun create(storageDir: File): Uri {
            return WorkWithFiles.getInstance().fileCreate(storageDir)
        }

        fun getCurrentPhotoPath(): String {
            return WorkWithFiles.getInstance().currentPhotoPath
        }

        fun getBitmap(currentPhotoPath: Uri, contentResolver: ContentResolver): Bitmap {
            return WorkWithFiles.getInstance().getBitmap(currentPhotoPath, contentResolver)
        }

        fun getBitmap(currentPhotoPath: String): Bitmap {
            return WorkWithFiles.getInstance().getBitmap(currentPhotoPath)
        }
    }
}