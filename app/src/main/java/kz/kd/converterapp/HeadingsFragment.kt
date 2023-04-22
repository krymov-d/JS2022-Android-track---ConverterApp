package kz.kd.converterapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class HeadingsFragment : Fragment(R.layout.fragment_headings) {

    private lateinit var tvHeadingOne: TextView
    private lateinit var tvHeadingTwo: TextView
    private lateinit var tvHeadingThree: TextView
    private lateinit var tvHeadingFour: TextView
    private lateinit var tvHeadingFive: TextView
    private lateinit var tvHeadingSix: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    private fun initViews(view: View) {
        with(view) {
            tvHeadingOne = findViewById(R.id.news_tv_heading_one)
            tvHeadingTwo = findViewById(R.id.news_tv_heading_two)
            tvHeadingThree = findViewById(R.id.news_tv_heading_three)
            tvHeadingFour = findViewById(R.id.news_tv_heading_four)
            tvHeadingFive = findViewById(R.id.news_tv_heading_five)
            tvHeadingSix = findViewById(R.id.news_tv_heading_six)
        }
    }
}