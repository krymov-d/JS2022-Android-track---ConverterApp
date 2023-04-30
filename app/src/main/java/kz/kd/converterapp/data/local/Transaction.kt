package kz.kd.converterapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey
    val id: Int,
    val amount: String,
    val currency: String,
    val flagURL: String,
)