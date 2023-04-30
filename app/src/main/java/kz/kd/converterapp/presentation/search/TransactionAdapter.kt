package kz.kd.converterapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import kz.kd.converterapp.R
import kz.kd.converterapp.domain.models.TransactionHistory

class TransactionAdapter(
    private val layoutInflater: LayoutInflater,
) : androidx.recyclerview.widget.ListAdapter<TransactionHistory, TransactionViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = layoutInflater.inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<TransactionHistory>() {
    override fun areItemsTheSame(
        oldItem: TransactionHistory,
        newItem: TransactionHistory
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TransactionHistory,
        newItem: TransactionHistory
    ): Boolean {
        return oldItem == newItem
    }
}