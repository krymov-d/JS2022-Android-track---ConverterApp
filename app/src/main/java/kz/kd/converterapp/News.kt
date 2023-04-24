package kz.kd.converterapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: Int,
    val author: String,
    val name: String,
    val body: String,
) : Parcelable
