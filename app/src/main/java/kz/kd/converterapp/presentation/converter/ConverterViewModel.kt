package kz.kd.converterapp.presentation.converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.kd.converterapp.domain.models.Currency

class ConverterViewModel : ViewModel() {

    val selectedCurrencyLiveData: MutableLiveData<Currency> = MutableLiveData()

    fun deleteCurrency() {

    }

    fun recoverCurrency() {

    }

}