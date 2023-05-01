package kz.kd.converterapp.presentation.converter

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import kz.kd.converterapp.R
import kz.kd.converterapp.domain.models.Currency

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val etAmount: EditText = itemView.findViewById(R.id.item_currency_et_amount)
    private val tlAmount: TextInputLayout = itemView.findViewById(R.id.item_currency_etl)
    private val ivFlag: ImageView = itemView.findViewById(R.id.item_currency_iv_flag)
    private val tvCountry: TextView = itemView.findViewById(R.id.item_currency_tv_currency)

    fun bind(currency: Currency) {
        etAmount.setText(currency.amount.toString())
        tlAmount.hint = currency.currencyName
        ivFlag.setImageResource(currency.countryFlag)
        tvCountry.text = currency.countryName
    }
}