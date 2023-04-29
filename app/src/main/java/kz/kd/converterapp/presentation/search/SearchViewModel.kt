package kz.kd.converterapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    private val _transactionsLiveData: MutableLiveData<List<TemporaryClass>> = MutableLiveData()
    val transactionsLiveData: LiveData<List<TemporaryClass>> = _transactionsLiveData

    init {
        val transactionOne = TemporaryClass(id = 0, amount = "10000", currency = "KZT")
        val transactionTwo = TemporaryClass(id = 1, amount = "20000", currency = "KZT")
        val transactionThree = TemporaryClass(id = 2, amount = "30000", currency = "KZT")
        val transactionFour = TemporaryClass(id = 3, amount = "40000", currency = "KZT")

        _transactionsLiveData.postValue(
            listOf(
                transactionOne,
                transactionTwo,
                transactionThree,
                transactionFour
            )
        )
    }
}