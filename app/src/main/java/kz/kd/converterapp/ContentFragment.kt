package kz.kd.converterapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val NEWS_TAG = "NEWS_TAG"

class ContentFragment : Fragment(R.layout.fragment_content) {

    companion object {
        fun newInstance(news: News): ContentFragment {
            val args = Bundle()
            args.putParcelable(NEWS_TAG, news)

            val fragment = ContentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var tvContentName: TextView
    private lateinit var tvContentAuthor: TextView
    private lateinit var tvContentBody: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initContent()
    }

    private fun initViews(view: View) {
        with(view) {
            tvContentName = findViewById(R.id.news_tv_content_name)
            tvContentAuthor = findViewById(R.id.news_tv_content_author)
            tvContentBody = findViewById(R.id.news_tv_content_body)
        }
    }

    private fun initContent() {
        val news: News = arguments?.getParcelable(NEWS_TAG) ?: return
        tvContentName.text = news.name
        tvContentAuthor.text = news.author
        tvContentBody.text = news.body
    }
}