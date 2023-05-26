package kz.kd.converterapp.presentation.account.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import kz.kd.converterapp.R
import kz.kd.converterapp.presentation.utils.startCamera
import kz.kd.converterapp.presentation.utils.startEmail
import kz.kd.converterapp.presentation.utils.startPhoneCall
import kz.kd.converterapp.presentation.utils.startWebsite

class AccountItemMainFragment : Fragment() {

    private lateinit var btnShare: AppCompatButton
    private lateinit var btnSend: AppCompatButton
    private lateinit var btnCall: AppCompatButton
    private lateinit var btnCamera: AppCompatButton

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
        initImplicitIntents()
    }

    private fun initViews(view: View) {
        with(view) {
            btnShare = findViewById(R.id.tab_item_account_btn_share)
            btnSend = findViewById(R.id.tab_item_account_btn_send)
            btnCall = findViewById(R.id.tab_item_account_btn_call)
            btnCamera = findViewById(R.id.tab_item_account_btn_camera)
        }
    }

    private fun initImplicitIntents() {
        val currentContext = context ?: return

        btnShare.setOnClickListener {
            currentContext.startWebsite("starwars.fandom.com/wiki/Anakin_Skywalker")
        }

        btnSend.setOnClickListener {
            currentContext.startEmail("darthvader@emprire.sw")
        }

        btnCall.setOnClickListener {
            currentContext.startPhoneCall("+7-777-777-77-77")
        }

        btnCamera.setOnClickListener {
            currentContext.startCamera()
        }
    }
}