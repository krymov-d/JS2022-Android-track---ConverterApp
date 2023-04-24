package kz.kd.converterapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

private const val HEADINGS_FRAGMENT_TAG = "HEADINGS_FRAGMENT_TAG"

class ThirdActivity : AppCompatActivity() {

    private lateinit var btnBackward: Button
    private lateinit var btnForward: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        initViews()
        initClickListeners()
        initHeadings()
    }

    private fun initViews() {
        btnBackward = findViewById(R.id.news_btn_backward)
        btnForward = findViewById(R.id.news_btn_forward)
    }

    private fun initClickListeners() {
        btnBackward.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        btnForward.setOnClickListener {
            val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.news_fl_headings)
            if (fragment is HeadingsFragment) {
                fragment.showSucceedingNews()
            }
        }
    }

    private fun initHeadings() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.news_fl_headings, HeadingsFragment(), HEADINGS_FRAGMENT_TAG)
            .commit()
    }
}