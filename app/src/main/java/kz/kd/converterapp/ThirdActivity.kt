package kz.kd.converterapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var btnBackward: Button
    private lateinit var btnForward: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        initViews()
        initHeadings()
    }

    private fun initViews() {
        btnBackward = findViewById(R.id.news_btn_backward)
        btnForward = findViewById(R.id.news_btn_forward)
    }

    private fun initHeadings() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.news_fl_headings, HeadingsFragment())
            .commit()
    }
}