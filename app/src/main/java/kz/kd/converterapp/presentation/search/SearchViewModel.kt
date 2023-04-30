package kz.kd.converterapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.kd.converterapp.domain.models.TransactionHistory

class SearchViewModel : ViewModel() {
    private val _transactionsLiveData: MutableLiveData<List<TransactionHistory>> = MutableLiveData()
    val transactionsLiveData: LiveData<List<TransactionHistory>> = _transactionsLiveData

    init {
        val transactionOne =
            TransactionHistory(
                id = 0,
                amount = "15 000",
                currency = "Казахстан, тенге",
                flagURL = ""
            )
        val transactionTwo =
            TransactionHistory(
                id = 1,
                amount = "2 000",
                currency = "Казахстан, тенге",
                flagURL = ""
            )
        val transactionThree =
            TransactionHistory(
                id = 2,
                amount = "765 343 342 34",
                currency = "Казахстан, тенге",
                flagURL = ""
            )
        val transactionFour =
            TransactionHistory(
                id = 3,
                amount = "123 456 789",
                currency = "Казахстан, тенге",
                flagURL = ""
            )

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