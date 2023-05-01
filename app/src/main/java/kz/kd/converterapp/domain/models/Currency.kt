package kz.kd.converterapp.domain.models

data class Currency(
    val id: Int,
    val amount: Double,
    val countryFlag: Int,
    val countryName: String,
    val currencyName: String
)
