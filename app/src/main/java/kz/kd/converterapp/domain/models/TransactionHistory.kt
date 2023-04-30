package kz.kd.converterapp.domain.models

data class TransactionHistory(
    val id: Int,
    val amount: String,
    val currency: String,
    val flagURL: String,
)
