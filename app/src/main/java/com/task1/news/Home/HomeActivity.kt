package com.task1.news.Home

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.task1.news.News.NewsAdapter
import com.task1.news.R
import com.task1.news.News.NewsFragment
import com.task1.news.category.Category
import com.task1.news.category.CategoriesFragment
import com.task1.news.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {


    lateinit var categories: CategoriesFragment
    lateinit var news: NewsFragment
    lateinit var ic_menu: ImageView
    lateinit var adapter: NewsAdapter
    lateinit var activityHomeBinding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setContentView(activityHomeBinding.root)

        initViews()
        pushFragment(categories)

        //Handling Navigation View
        activityHomeBinding.navigationView.setNavigationItemSelectedListener {

            if (it.itemId == R.id.categories) {

                pushFragment(categories)
            }
            return@setNavigationItemSelectedListener true
        }

        activityHomeBinding.navigationView.setCheckedItem(R.id.categories)

        //Menu Click
        ic_menu.setOnClickListener {

            activityHomeBinding.drawerLayout.open()
        }



        categories.onCategoryClick = object : CategoriesFragment.onCategoryClickListener {
            override fun onClick(position: Int, Category: Category) {
                pushFragment(NewsFragment.getInstance(Category), true)


            }
        }
    }

    fun initViews() {
        ic_menu = findViewById(R.id.ic_menu)
        adapter = NewsAdapter(null)
        categories = CategoriesFragment()
        news = NewsFragment()
    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {


        var fragment = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_Container, fragment)


        if (addToBackStack) {
            fragment.addToBackStack("j")


        }
        fragment.commit()
        activityHomeBinding.drawerLayout.close()
    }


}