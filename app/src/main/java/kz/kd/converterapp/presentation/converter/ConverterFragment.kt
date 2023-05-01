package kz.kd.converterapp.presentation.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R
import kz.kd.converterapp.domain.models.Currency
import kz.kd.converterapp.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConverterFragment : Fragment() {

    private val converterViewModel: ConverterViewModel by viewModel()

    private lateinit var rvConverter: RecyclerView
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager
    private lateinit var currencyItemDecoration: SpaceItemDecoration
    private lateinit var currencySmoothScroller: LinearSmoothScroller
    private lateinit var btnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initRecyclerProperties()
        initRecycler()
        initButtonClickListener()

        createContent()
    }

    private fun initViews(view: View) {
        with(view) {
            rvConverter = findViewById(R.id.converter_rv)
            btnAdd = findViewById(R.id.fragment_converter_btn_add)
        }
    }

    private fun initRecyclerProperties() {
        val currentContext = context ?: return
        currencyAdapter = CurrencyAdapter(layoutInflater = layoutInflater)
        currencyLayoutManager = LinearLayoutManager(currentContext)
        currencyItemDecoration = SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 8)
        currencySmoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int = SNAP_TO_START
        }
    }

    private fun initRecycler() {
        rvConverter.apply {
            adapter = currencyAdapter
            layoutManager = currencyLayoutManager
            addItemDecoration(currencyItemDecoration)
        }
    }

    private fun initButtonClickListener() {
        btnAdd.setOnClickListener {
            val item = Currency(
                id = 99,
                amount = 99999.99999,
                countryFlag = R.drawable.ic_anakin_skywalker,
                countryName = "Kazakhstan",
                currencyName = "KZT"
            )

            currencyAdapter.addCurrency(item)
            currencySmoothScroller.targetPosition = currencyAdapter.itemCount
            currencyLayoutManager.startSmoothScroll(currencySmoothScroller)
        }
    }

    private fun createContent() {
        val itemList = mutableListOf<Currency>()
        for (i in 0..10) {
            val item = Currency(
                id = i,
                amount = i * 10.0,
                countryFlag = R.drawable.ic_anakin_skywalker,
                countryName = "Kazakhstan",
                currencyName = "KZT"
            )
            itemList.add(item)
        }
        currencyAdapter.updateDataSet(itemList)
    }
}