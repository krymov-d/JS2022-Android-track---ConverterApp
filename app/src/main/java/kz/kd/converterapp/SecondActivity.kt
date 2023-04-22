package kz.kd.converterapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var btnShare: Button
    private lateinit var btnSend: Button
    private lateinit var btnCall: Button
    private lateinit var btnCamera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initViews()
        initImplicitIntents()
    }

    private fun initViews() {
        btnShare = findViewById(R.id.profile_btn_share)
        btnSend = findViewById(R.id.profile_btn_send)
        btnCall = findViewById(R.id.profile_btn_call)
        btnCamera = findViewById(R.id.profile_btn_camera)
    }

    private fun initImplicitIntents() {
        btnShare.setOnClickListener {
            startWebsite("starwars.fandom.com/wiki/Anakin_Skywalker")
        }

        btnSend.setOnClickListener {
            startEmail("darthvader@emprire.sw")
        }

        btnCall.setOnClickListener {
            startPhoneCall("+7-777-777-77-77")
        }

        btnCamera.setOnClickListener {
            startCamera()
        }
    }
}