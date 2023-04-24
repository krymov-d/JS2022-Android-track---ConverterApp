package kz.kd.converterapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class AccountFragment : Fragment() {

    private lateinit var btnShare: Button
    private lateinit var btnSend: Button
    private lateinit var btnCall: Button
    private lateinit var btnCamera: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initImplicitIntents()
    }

    private fun initViews(view: View) {
        with(view) {
            btnShare = findViewById(R.id.profile_btn_share)
            btnSend = findViewById(R.id.profile_btn_send)
            btnCall = findViewById(R.id.profile_btn_call)
            btnCamera = findViewById(R.id.profile_btn_camera)
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