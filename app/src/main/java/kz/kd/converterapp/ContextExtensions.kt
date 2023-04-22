package kz.kd.converterapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun Context.showToast(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, length).show()
}

fun Context.startEmail(email: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
    startActivity(emailIntent)
}

fun Context.startPhoneCall(number: String) {
    val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
    startActivity(phoneIntent)
}

fun Context.startWebsite(website: String) {
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$website"))
    startActivity(webIntent)
}

fun Context.startCamera() {
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivity(cameraIntent)
}