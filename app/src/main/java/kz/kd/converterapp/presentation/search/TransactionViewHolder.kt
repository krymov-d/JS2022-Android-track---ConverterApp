package kz.kd.converterapp.presentation.search

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R
import kz.kd.converterapp.domain.models.TransactionHistory

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvAmount: TextView = itemView.findViewById(R.id.item_transaction_tv_amount)
    private val tvCurrency: TextView = itemView.findViewById(R.id.item_transaction_tv_currency)

    fun bind(transaction: TransactionHistory) {
        tvAmount.text = transaction.amount
        tvCurrency.text = transaction.currency
    }
}