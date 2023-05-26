package kz.kd.converterapp.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kz.kd.converterapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private val accountViewModel: AccountViewModel by viewModel()

    private lateinit var accountTabLayout: TabLayout
    private lateinit var accountViewPager2: ViewPager2
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initViewPager()
    }

    private fun initViews(view: View) {
        with(view) {
            accountTabLayout = findViewById(R.id.account_tab_layout)
            accountViewPager2 = findViewById(R.id.account_view_pager)
        }
    }

    private fun initViewPager() {
        accountAdapter = AccountAdapter(this)
        accountViewPager2.adapter = accountAdapter
    }

}