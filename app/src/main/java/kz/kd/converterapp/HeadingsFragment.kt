package kz.kd.converterapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class HeadingsFragment : Fragment(R.layout.fragment_headings) {

    private val headingsList = mutableListOf<TextView>()
    private val newsList = mutableListOf<News>()

    private lateinit var tvHeadingOne: TextView
    private lateinit var tvHeadingTwo: TextView
    private lateinit var tvHeadingThree: TextView
    private lateinit var tvHeadingFour: TextView
    private lateinit var tvHeadingFive: TextView
    private lateinit var tvHeadingSix: TextView

    private lateinit var newsOne: News
    private lateinit var newsTwo: News
    private lateinit var newsThree: News
    private lateinit var newsFour: News
    private lateinit var newsFive: News
    private lateinit var newsSix: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNews()
        initNewsList()
    }

    private fun initNews() {
        newsOne = News(
            id = 1,
            author = getString(R.string.news_tv_author_one),
            name = getString(R.string.news_tv_heading_one),
            body = getString(R.string.news_tv_content_one)
        )
        newsTwo = News(
            id = 1,
            author = getString(R.string.news_tv_author_two),
            name = getString(R.string.news_tv_heading_two),
            body = getString(R.string.news_tv_content_two)
        )
        newsThree = News(
            id = 1,
            author = getString(R.string.news_tv_author_three),
            name = getString(R.string.news_tv_heading_three),
            body = getString(R.string.news_tv_content_three)
        )
        newsFour = News(
            id = 1,
            author = getString(R.string.news_tv_author_four),
            name = getString(R.string.news_tv_heading_four),
            body = getString(R.string.news_tv_content_four)
        )
        newsFive = News(
            id = 1,
            author = getString(R.string.news_tv_author_five),
            name = getString(R.string.news_tv_heading_five),
            body = getString(R.string.news_tv_content_five)
        )
        newsSix = News(
            id = 1,
            author = getString(R.string.news_tv_author_six),
            name = getString(R.string.news_tv_heading_six),
            body = getString(R.string.news_tv_content_six)
        )
    }

    private fun initNewsList() {
        newsList.addAll(listOf(newsOne, newsTwo, newsThree, newsFour, newsFive, newsSix))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initHeadingsList()
        initHeadingNames()
        initTransaction()
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

    private fun initHeadingsList() {
        headingsList.addAll(
            listOf(
                tvHeadingOne,
                tvHeadingTwo,
                tvHeadingThree,
                tvHeadingFour,
                tvHeadingFive,
                tvHeadingSix
            )
        )
    }

    private fun initHeadingNames() {
        tvHeadingOne.text = newsOne.name
        tvHeadingTwo.text = newsTwo.name
        tvHeadingThree.text = newsThree.name
        tvHeadingFour.text = newsFour.name
        tvHeadingFive.text = newsFive.name
        tvHeadingSix.text = newsSix.name
    }

    private fun initTransaction() {
        for (i in 0..5) {
            headingsList[i].setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.news_fl_content, ContentFragment.newInstance(newsList[i]))
                    .addToBackStack(null).commit()
            }
        }
    }
}