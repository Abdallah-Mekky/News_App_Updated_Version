package com.task1.news.newVersion.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.task1.news.Api.ApiManager
import com.task1.news.Constants
import com.task1.news.Model.ArticlesItem
import com.task1.news.Model.NewsResponse
import com.task1.news.Model.SourcesItem
import com.task1.news.Model.SourcesResponse
import com.task1.news.NewsAdapter
import com.task1.news.R
import com.task1.news.newVersion.NewsDetailsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment() : Fragment() {

    companion object {

        var items: ArticlesItem? = null
        var tagg: SourcesItem? = null
        fun getInstance(category: category): NewsFragment {

            var fragment = NewsFragment()
            fragment.category = category
            return fragment
        }


    }


    lateinit var category: category
    lateinit var tapLayout: TabLayout
    lateinit var progressBarSourses: ProgressBar
    lateinit var newsProgressBar: ProgressBar
    lateinit var newsRecyclerView: RecyclerView
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsDetailsFragment: NewsDetailsFragment
    lateinit var search: androidx.appcompat.widget.SearchView
    lateinit var name: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onStop() {
        super.onStop()
        search.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()
        getNewsSources()

        newsAdapter.onItemClick = object : NewsAdapter.OnItemClickListener {

            override fun onClick(position: Int, item: ArticlesItem) {

                items = item
                newsDetailsFragment = NewsDetailsFragment()
                var fragmentManager: FragmentManager = activity?.supportFragmentManager!!
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_Container, newsDetailsFragment)
                fragmentTransaction.addToBackStack("d")
                fragmentTransaction.commit()

            }


        }

        search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                getNewsBySource(tagg, query!!.lowercase())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                getNewsBySource(tagg, newText!!.lowercase())
                return true
            }


        })


    }


    fun initViews() {

        tapLayout = requireActivity().findViewById(R.id.tabLayout)
        progressBarSourses = requireActivity().findViewById(R.id.progressBarSourses)
        newsRecyclerView = requireActivity().findViewById(R.id.newsRecyclerView)
        newsProgressBar = requireActivity().findViewById(R.id.newsProgressBar)
        newsAdapter = NewsAdapter(null)
        newsRecyclerView.adapter = newsAdapter
        search = requireActivity().findViewById(R.id.abd)
        name = requireActivity().findViewById(R.id.category_name)

        controlSearchView()


    }


    fun controlSearchView() {

        search.isVisible = true

        search.setOnSearchClickListener {
            name.text = null
        }

        search.setOnCloseListener(SearchView.OnCloseListener {

            name.text = "News App"
            getNewsBySource(tagg, null)

            return@OnCloseListener false
        })

    }

    fun getNewsSources() {


        ApiManager.getApis().getNewsSources(Constants.apiKey, category.id).enqueue(object :
            Callback<SourcesResponse> {

            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {

                progressBarSourses.isVisible = false
                addSourcesToTabLayout(response.body()?.sources)
                Log.e("response", response.body().toString())

            }


            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                progressBarSourses.isVisible = false
                Log.e("error", t.localizedMessage)
            }

        })
    }

    fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {

        sources?.forEach {
            val tap = tapLayout.newTab()
            tap.setText(it?.name + "")
            tap.tag = it
            tapLayout.addTab(tap)
        }

        tapLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                newsProgressBar.isVisible = false
                val sourceClicked = tab?.tag as SourcesItem
                tagg = sourceClicked

                getNewsBySource(sourceClicked, "")

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

                newsProgressBar.isVisible = false
                val sourceClicked = tab?.tag as SourcesItem
                tagg = sourceClicked

                getNewsBySource(sourceClicked, "")
            }

        })

        tapLayout.getTabAt(0)?.select()
    }

    fun getNewsBySource(sourceClicked: SourcesItem?, query: String?) {

        ApiManager.getApis().getNewsBySource(Constants.apiKey, sourceClicked?.id ?: "", query ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {


                    newsAdapter.refreashAdapter(response.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                    Log.e("news Error", t.localizedMessage)
                }

            })

    }


}