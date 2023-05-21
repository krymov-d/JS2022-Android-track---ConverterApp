package kz.kd.converterapp.presentation.converter.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import kz.kd.converterapp.R

class CurrencyAddBottomSheet : BottomSheetDialogFragment() {

    private lateinit var etAmount: TextInputEditText
    private lateinit var btnSelect: AppCompatButton
    private lateinit var btnAdd: AppCompatButton
    private lateinit var rvCurrencies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_currency_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initClickListeners()
    }

    private fun initViews(view: View) {
        with(view) {
            etAmount = findViewById(R.id.bs_currency_add_et_amount)
            btnSelect = findViewById(R.id.bs_currency_add_btn_select_currency)
            btnAdd = findViewById(R.id.bs_currency_add_btn_add_currency)
            rvCurrencies = findViewById(R.id.bs_currency_add_rv_currencies)
        }
    }

    private fun initClickListeners() {
        btnSelect.setOnClickListener {
            it.isGone = true
            rvCurrencies.isGone = false
        }

        btnAdd.setOnClickListener {
            dismiss()
        }
    }
}