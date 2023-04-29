package kz.kd.converterapp.presentation.search

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvAmount: TextView = itemView.findViewById(R.id.item_transaction_tv_amount)
    private val tvCurrency: TextView = itemView.findViewById(R.id.item_transaction_tv_currency)

    fun bind(tc: TemporaryClass) {
        tvAmount.text = tc.amount
        tvCurrency.text = tc.currency
    }
}