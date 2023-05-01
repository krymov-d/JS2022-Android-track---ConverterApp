package kz.kd.converterapp.presentation.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R
import kz.kd.converterapp.domain.models.Currency
import kz.kd.converterapp.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConverterFragment : Fragment() {

    private val converterViewModel: ConverterViewModel by viewModel()

    private lateinit var rvConverter: RecyclerView
    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initAdapter()
        initRecycler()

        createContent()
    }

    private fun initViews(view: View) {
        rvConverter = view.findViewById(R.id.converter_rv)
    }

    private fun initAdapter() {
        currencyAdapter = CurrencyAdapter(layoutInflater = layoutInflater)
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvConverter.adapter = currencyAdapter
        rvConverter.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 8)
        rvConverter.addItemDecoration(spaceItemDecoration)
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