package kz.kd.converterapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import kz.kd.converterapp.R

class SearchAdapter(
    private val layoutInflater: LayoutInflater,
) : androidx.recyclerview.widget.ListAdapter<TemporaryClass, SearchViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = layoutInflater.inflate(R.layout.item_transaction, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<TemporaryClass>() {
    override fun areItemsTheSame(oldItem: TemporaryClass, newItem: TemporaryClass): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TemporaryClass, newItem: TemporaryClass): Boolean {
        return oldItem == newItem
    }
}