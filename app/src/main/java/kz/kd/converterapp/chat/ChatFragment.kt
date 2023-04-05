package kz.kd.converterapp.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kz.kd.converterapp.R

private const val KEY_NAME = "Name"

class ChatFragment : Fragment(), IFSaveName {

    private lateinit var preferences: SharedPreferences
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSavedName()
        initBSDNameRequest()
    }

    private fun getSavedName() {
        preferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        userName = preferences.getString(KEY_NAME, "")
    }

    private fun initBSDNameRequest() {
        BSDNameRequest(userName, this).show(childFragmentManager, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun saveName(name: String) {
        preferences.edit().putString(KEY_NAME, name).apply()
    }
}