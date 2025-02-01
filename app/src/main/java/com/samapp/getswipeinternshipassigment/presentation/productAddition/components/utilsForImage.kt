package com.samapp.getswipeinternshipassigment.presentation.productAddition.components

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.IOException
import java.io.File
import java.io.FileOutputStream
fun prepareTextField(name: String): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), name)
}
fun convertUriToFile(uri: Uri, context: Context): File? {
    val contentResolver: ContentResolver = context.contentResolver
    val fileName = getFileName(uri, contentResolver)

    return try {
        val tempFile = File(context.cacheDir, fileName ?: "image.jpg")
        val inputStream = contentResolver.openInputStream(uri) ?: return null
        val outputStream = FileOutputStream(tempFile)

        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()

        tempFile
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
fun getFileName(uri: Uri, contentResolver: ContentResolver): String? {
    var name: String? = null
    val cursor = contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (it.moveToFirst()) {
            name = it.getString(nameIndex)
        }
    }
    return name
}
fun prepareImageFilePart(uri: Uri, context: Context): MultipartBody.Part? {
    val file = convertUriToFile(uri, context)
    return if (file != null) {
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        MultipartBody.Part.createFormData("image", file.name, requestBody)
    } else {
        null
    }
}