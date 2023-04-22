package kz.kd.converterapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val KEY_PIN_CODE = "PinCode"

class MainActivity : AppCompatActivity() {

    private var pinCode = ""

    private lateinit var tvCode: TextView
    private var btnList = mutableListOf<Button>()

    private lateinit var btnZero: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button

    private lateinit var btnClear: Button
    private lateinit var btnOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        restorePinCode(savedInstanceState)
        initClickListeners()
        initCodeVerifier()
    }

    private fun restorePinCode(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            return
        }
        pinCode = savedInstanceState.getString(KEY_PIN_CODE, pinCode)
        tvCode.text = pinCode
    }

    private fun initViews() {
        tvCode = findViewById(R.id.pin_tv_code)

        btnZero = findViewById(R.id.pin_btn_zero)
        btnOne = findViewById(R.id.pin_btn_one)
        btnTwo = findViewById(R.id.pin_btn_two)
        btnThree = findViewById(R.id.pin_btn_three)
        btnFour = findViewById(R.id.pin_btn_four)
        btnFive = findViewById(R.id.pin_btn_five)
        btnSix = findViewById(R.id.pin_btn_six)
        btnSeven = findViewById(R.id.pin_btn_seven)
        btnEight = findViewById(R.id.pin_btn_eight)
        btnNine = findViewById(R.id.pin_btn_nine)
        btnClear = findViewById(R.id.pin_btn_clear)
        btnOk = findViewById(R.id.pin_btn_ok)

        btnList.addAll(
            listOf(
                btnZero,
                btnOne,
                btnTwo,
                btnThree,
                btnFour,
                btnFive,
                btnSix,
                btnSeven,
                btnEight,
                btnNine
            )
        )
    }

    private fun initClickListeners() {
        for (btn in btnList) {
            btn.setOnClickListener {
                pinCode += btn.text
                tvCode.text = pinCode
            }
        }
    }

    private fun initCodeVerifier() {
        btnOk.setOnClickListener {
            if (pinCode == CORRECT_PIN_CODE) {
                Toast.makeText(this, R.string.pin_correct, Toast.LENGTH_SHORT).show()
                Intent(this, SecondActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_PIN_CODE, tvCode.text.toString())
    }
}