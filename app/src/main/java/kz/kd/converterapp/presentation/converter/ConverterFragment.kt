package kz.kd.converterapp.presentation.converter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R
import kz.kd.converterapp.domain.models.Currency
import kz.kd.converterapp.presentation.utils.ClickListener
import kz.kd.converterapp.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConverterFragment : Fragment() {

    private val converterViewModel: ConverterViewModel by viewModel()

    private lateinit var rvConverter: RecyclerView
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager
    private lateinit var currencyItemDecoration: SpaceItemDecoration
    private lateinit var currencySmoothScroller: LinearSmoothScroller
    private lateinit var currencyItemTouchHelperCallback: ItemTouchHelper.SimpleCallback
    private lateinit var currencyItemTouchHelper: ItemTouchHelper
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
        initBtnAddClickListener()
        initMenu()

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
        initAdapter()
        initLayoutManager(currentContext)
        initItemDecoration()
        initSmoothScroller()
        initItemTouchHelper()
    }

    private fun initAdapter() {
        currencyAdapter = CurrencyAdapter(layoutInflater = layoutInflater)
        currencyAdapter.listener = ClickListener {

        }
    }

    private fun initLayoutManager(context: Context) {
        currencyLayoutManager = LinearLayoutManager(context)
    }

    private fun initItemDecoration() {
        currencyItemDecoration = SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 8)
    }

    private fun initSmoothScroller() {
        currencySmoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int = SNAP_TO_START
        }
    }

    private fun initItemTouchHelper() {
        currencyItemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(UP or DOWN, START or END) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    currencyAdapter.moveCurrency(viewHolder.layoutPosition, target.layoutPosition)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    currencyAdapter.deleteCurrency(viewHolder.layoutPosition)
                }
            }
        currencyItemTouchHelper = ItemTouchHelper(currencyItemTouchHelperCallback)
    }

    private fun initRecycler() {
        rvConverter.apply {
            adapter = currencyAdapter
            layoutManager = currencyLayoutManager
            addItemDecoration(currencyItemDecoration)
        }
        currencyItemTouchHelper.attachToRecyclerView(rvConverter)
    }

    private fun initBtnAddClickListener() {
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

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_converter, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                menuItem.isChecked = !menuItem.isChecked
                return when (menuItem.itemId) {
                    R.id.menu_sort_alphabetically -> {
                        currencyAdapter.sortCurrency(1)
                        true
                    }

                    R.id.menu_sort_numerically -> {
                        currencyAdapter.sortCurrency(2)
                        true
                    }

                    R.id.menu_reset -> {
                        activity?.invalidateOptionsMenu()
                        currencyAdapter.sortCurrency(0)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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