package kz.kd.converterapp.presentation.converter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R
import kz.kd.converterapp.domain.models.Currency

class CurrencyAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    private val currencyList = mutableListOf<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    fun updateDataSet(newDataSet: List<Currency>) {
        currencyList.clear()
        currencyList.addAll(newDataSet)
        notifyItemRangeChanged(0, newDataSet.size)
    }

    fun addCurrency(newCurrency: Currency) {
        currencyList.add(currencyList.size, newCurrency)
        notifyItemRangeChanged(0, currencyList.size)
    }

    fun moveCurrency(startPosition: Int, endPosition: Int) {
        val temporaryCurrency: Currency = currencyList[endPosition]
        currencyList[endPosition] = currencyList[startPosition]
        currencyList[startPosition] = temporaryCurrency
        notifyItemMoved(startPosition, endPosition)
    }

    fun deleteCurrency(position: Int) {
        currencyList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun sortCurrency(sortType: Int) {
        when (sortType) {
            0 -> {
                currencyList.sortedBy { it.id }
            }

            1 -> {
                currencyList.sortedBy { it.currencyName }
            }

            2 -> {
                currencyList.sortedBy { it.amount }
            }
        }
        notifyItemRangeChanged(0, currencyList.size)
    }
}