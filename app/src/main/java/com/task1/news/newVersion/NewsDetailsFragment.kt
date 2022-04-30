package com.task1.news.newVersion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.task1.news.Model.ArticlesItem
import com.task1.news.R
import com.task1.news.newVersion.ui.NewsFragment
import com.task1.news.newVersion.ui.NewsFragment.Companion.items

class NewsDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_new_details, container, false)
    }

    lateinit var news_Details_Image: ImageView
    lateinit var news_Details_Source_Name: TextView
    lateinit var news_Details_Title: TextView
    lateinit var news_Details_Time: TextView
    lateinit var news_Details_Description: TextView
    lateinit var news_Details_Full_Article: TextView
    lateinit var NewsFragment: NewsFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        Glide.with(requireContext())
            .load(items?.urlToImage)
            .into(news_Details_Image)

        news_Details_Source_Name.text = items?.source?.name
        news_Details_Title.text = items?.title
        news_Details_Time.text = items?.publishedAt
        news_Details_Description.text = items?.description

        var url = items?.url

        news_Details_Full_Article.setOnClickListener {

            var intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context?.startActivity(intent)


        }


    }

    private fun initViews() {

        NewsFragment = com.task1.news.newVersion.ui.NewsFragment()
        news_Details_Image = requireView().findViewById(R.id.news_Details_Image)
        news_Details_Source_Name = requireView().findViewById(R.id.news_Details_Source_Name)
        news_Details_Title = requireView().findViewById(R.id.news_Details_Title)
        news_Details_Time = requireView().findViewById(R.id.news_Details_Time)
        news_Details_Description = requireView().findViewById(R.id.news_Details_Description)
        news_Details_Full_Article = requireView().findViewById(R.id.news_Details_Full_Article)

    }


}