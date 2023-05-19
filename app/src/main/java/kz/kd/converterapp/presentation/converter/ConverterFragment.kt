package kz.kd.converterapp.presentation.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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
import kz.kd.converterapp.presentation.converter.delete.CurrencyDeleteDialogFragment
import kz.kd.converterapp.presentation.utils.ClickListener
import kz.kd.converterapp.presentation.utils.ClickListenerWithNoParameters
import kz.kd.converterapp.presentation.utils.SpaceItemDecoration
import kz.kd.converterapp.presentation.utils.showSnackBarWithAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConverterFragment : Fragment() {

    private val vmConverter: ConverterViewModel by viewModel()

    private lateinit var currentActivity: FragmentActivity
    private lateinit var tbMain: Toolbar
    private lateinit var tbItemSelected: Toolbar
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    private lateinit var rvConverter: RecyclerView
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager
    private lateinit var currencyItemDecoration: SpaceItemDecoration
    private lateinit var currencySmoothScroller: LinearSmoothScroller
    private lateinit var currencyItemTouchHelperCallback: ItemTouchHelper.SimpleCallback
    private lateinit var currencyItemTouchHelper: ItemTouchHelper

    private lateinit var btnAddCurrency: AppCompatButton
    private lateinit var btnDeleteCurrency: AppCompatButton

    private lateinit var currencyDeleteDialogFragment: CurrencyDeleteDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentActivity = activity ?: return

        initToolBar()
        initViews(view)
        initRecyclerProperties()
        initRecycler()
        initClickListeners()
        initMenu()

        createContent()
    }

    private fun initToolBar() {
        with(currentActivity) {
            tbMain = findViewById(R.id.tb_main)
            tbItemSelected = findViewById(R.id.tb_item_selected)
            btnDeleteCurrency = findViewById(R.id.tb_item_selected_btn_delete)
        }
    }

    private fun initViews(view: View) {
        with(view) {
            rvConverter = findViewById(R.id.converter_rv)
            btnAddCurrency = findViewById(R.id.fragment_converter_btn_add)
        }
    }

    private fun initRecyclerProperties() {
        initAdapter()
        initItemLongClickListener()
        initLayoutManager()
        initItemDecoration()
        initSmoothScroller()
        initItemTouchHelper()
    }

    private fun initAdapter() {
        currencyAdapter = CurrencyAdapter(layoutInflater = layoutInflater)
    }

    private fun initItemLongClickListener() {
        currencyAdapter.listener = ClickListener { selectedCurrency ->
            substituteToolbar()
            setupOnBackPressedCallback()
            vmConverter.selectedCurrencyLiveData.value = selectedCurrency
        }
    }

    private fun substituteToolbar() {
        tbMain.isGone = !tbMain.isGone
        tbItemSelected.isGone = !tbItemSelected.isGone
    }

    private fun setupOnBackPressedCallback() {
        onBackPressedCallback =
            currentActivity.onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                substituteToolbar()
                remove()
            }
    }

    private fun initLayoutManager() {
        currencyLayoutManager = LinearLayoutManager(currentActivity)
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

    private fun initClickListeners() {
        btnAddCurrency.setOnClickListener {
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

        btnDeleteCurrency.setOnClickListener {
            substituteToolbar()
            onBackPressedCallback.remove()
            initCurrencyDeleteDialogFragment()
        }
    }

    private fun initCurrencyDeleteDialogFragment() {
        currencyDeleteDialogFragment = CurrencyDeleteDialogFragment()

        currencyDeleteDialogFragment.listener = ClickListenerWithNoParameters {
            vmConverter.deleteCurrency()
            showRecoverSnackBar()
        }

        currencyDeleteDialogFragment.show(parentFragmentManager, null)
    }

    private fun showRecoverSnackBar() {
        currentActivity.showSnackBarWithAction(
            btnDeleteCurrency,
            R.string.deleted,
            R.string.recover
        ) {
            vmConverter.recoverCurrency()
        }
    }

    private fun initMenu() {
        currentActivity.addMenuProvider(object : MenuProvider {
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
                        currentActivity.invalidateOptionsMenu()
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