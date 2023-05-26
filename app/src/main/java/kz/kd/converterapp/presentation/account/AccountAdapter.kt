package kz.kd.converterapp.presentation.account

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kz.kd.converterapp.presentation.account.items.AccountItemMainFragment
import kz.kd.converterapp.presentation.account.items.AccountItemStatisticsFragment

class AccountAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList =
        listOf(AccountItemMainFragment(), AccountItemStatisticsFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}