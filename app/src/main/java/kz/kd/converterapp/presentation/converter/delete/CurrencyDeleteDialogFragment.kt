package kz.kd.converterapp.presentation.converter.delete

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import kz.kd.converterapp.R
import kz.kd.converterapp.presentation.utils.ClickListenerWithNoParameters

class CurrencyDeleteDialogFragment : DialogFragment() {

    private lateinit var btnCancel: AppCompatButton
    private lateinit var btnConfirm: AppCompatButton
    var listener: ClickListenerWithNoParameters? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return inflater.inflate(R.layout.dialog_fragment_currency_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initClickListeners()
    }

    private fun initViews(view: View) {
        with(view) {
            btnCancel = findViewById(R.id.df_currency_delete_btn_cancel)
            btnConfirm = findViewById(R.id.df_currency_delete_btn_confirm)
        }
    }

    private fun initClickListeners() {
        btnCancel.setOnClickListener {
            dismiss()
        }

        btnConfirm.setOnClickListener {
            listener?.onClick()
            dismiss()
        }
    }
}