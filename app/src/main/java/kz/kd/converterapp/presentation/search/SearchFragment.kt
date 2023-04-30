package kz.kd.converterapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.kd.converterapp.R
import kz.kd.converterapp.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()

    private lateinit var rvTransactions: RecyclerView
    private lateinit var transactionsAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initAdapter()
        initRecycler()
        initObservers()
    }

    private fun initViews(view: View) {
        rvTransactions = view.findViewById(R.id.fragment_search_rv_transaction)
    }

    private fun initAdapter() {
        transactionsAdapter = TransactionAdapter(layoutInflater)
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvTransactions.adapter = transactionsAdapter
        rvTransactions.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 8)
        rvTransactions.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        searchViewModel.transactionsLiveData.observe(viewLifecycleOwner) {
            transactionsAdapter.submitList(it)
        }
    }
}