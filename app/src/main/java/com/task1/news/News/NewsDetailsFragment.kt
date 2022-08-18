package com.task1.news.News

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.task1.news.R
import com.task1.news.News.NewsFragment.Companion.items
import com.task1.news.databinding.FragmentNewDetailsBinding

class NewsDetailsFragment : Fragment() {


    lateinit var fragmentNewsDetailsBinding: FragmentNewDetailsBinding
    lateinit var NewsFragment: NewsFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentNewsDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_details, container, false)

        return fragmentNewsDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        showFullArticle()
    }

    private fun showFullArticle() {

        var url = items?.url

        fragmentNewsDetailsBinding.newsDetailsFullArticle.setOnClickListener {

            var intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context?.startActivity(intent)
        }
    }

    private fun initViews() {

        NewsFragment = NewsFragment()

        // Handling View
        Glide.with(requireContext())
            .load(items?.urlToImage)
            .into(fragmentNewsDetailsBinding.newsDetailsImage)
        fragmentNewsDetailsBinding.newsDetailsSourceName.text = items?.source?.name
        fragmentNewsDetailsBinding.newsDetailsTitle.text = items?.title
        fragmentNewsDetailsBinding.newsDetailsTime.text = items?.publishedAt
        fragmentNewsDetailsBinding.newsDetailsDescription.text = items?.description


    }
}