package kz.kd.converterapp.presentation.account.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import kz.kd.converterapp.R

class AccountItemStatisticsFragment : Fragment() {

    private lateinit var tvQuantity: AppCompatTextView
    private lateinit var tvTime: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_item_account_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    private fun initViews(view: View) {
        with(view) {
            tvQuantity = findViewById(R.id.tab_item_account_statistics_tv_quantity)
            tvTime = findViewById(R.id.tab_item_account_statistics_tv_time)
        }
    }
}