package com.task1.news.News

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.task1.news.category.Category
import com.task1.news.model.ArticlesItem
import com.task1.news.model.SourcesItem
import com.task1.news.R
import com.task1.news.databinding.FragmentNewsBinding

class NewsFragment() : Fragment() {

    // UI
    companion object {

        var items: ArticlesItem? = null
        var tagg: SourcesItem? = null
        fun getInstance(Category: Category): NewsFragment {

            var fragment = NewsFragment()
            fragment.Category = Category
            return fragment
        }


    }

    //UI
    lateinit var Category: Category
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsDetailsFragment: NewsDetailsFragment
    lateinit var search: androidx.appcompat.widget.SearchView
    lateinit var name: TextView
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsBinding: FragmentNewsBinding
//    lateinit var appBarHomeBinding: AppBarHomeBinding


    //UI
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        // appBarHomeBinding = DataBindingUtil.inflate(inflater,R.layout.app_bar_home,container,false)

        //return appBarHomeBinding.root

        return newsBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

    }


    override fun onStop() {
        super.onStop()
        search.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //UI
        initViews()

        subscribeToLiveData()
        newsViewModel.getNewsSources(Category)



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
                newsViewModel.getNewsBySource(tagg, query!!.lowercase())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsViewModel.getNewsBySource(tagg, newText!!.lowercase())
                return true
            }


        })


    }

    private fun subscribeToLiveData() {

        newsViewModel.sourcesLiveData.observe(viewLifecycleOwner,
            { sources -> addSourcesToTabLayout(sources) })

        newsViewModel.progressBarLiveData.observe(viewLifecycleOwner,
            { value -> newsBinding.progressBarSourses.isVisible = value!! })

        newsViewModel.newsLiveData.observe(viewLifecycleOwner,
            { news -> newsAdapter.refreashAdapter(news) })
    }


    //UI
    fun initViews() {


        newsAdapter = NewsAdapter(null)
        newsBinding.newsRecyclerView.adapter = newsAdapter
        search = requireActivity().findViewById(R.id.abd)
        name = requireActivity().findViewById(R.id.category_name)
        controlSearchView()


    }


    //UI
    fun controlSearchView() {

        search.isVisible = true

        search.setOnSearchClickListener {
            name.text = null
        }

        search.setOnCloseListener(SearchView.OnCloseListener {

            name.text = "News App"
            newsViewModel.getNewsBySource(tagg, null)

            return@OnCloseListener false
        })

    }


    //UI
    fun addSourcesToTabLayout(sources: List<SourcesItem?>?) {

        sources?.forEach {
            val tap = newsBinding.tabLayout.newTab()
            tap.setText(it?.name + "")
            tap.tag = it
            newsBinding.tabLayout.addTab(tap)
        }

        newsBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                newsBinding.newsProgressBar.isVisible = false
                val sourceClicked = tab?.tag as SourcesItem
                tagg = sourceClicked

                newsViewModel.getNewsBySource(sourceClicked, "")

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

                newsBinding.newsProgressBar.isVisible = false
                val sourceClicked = tab?.tag as SourcesItem
                tagg = sourceClicked
                newsViewModel.getNewsBySource(sourceClicked, "")
            }

        })

        newsBinding.tabLayout.getTabAt(0)?.select()
    }


}