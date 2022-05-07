package com.task1.news.newVersion

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.task1.news.NewsAdapter
import com.task1.news.R
import com.task1.news.newVersion.ui.NewsFragment
import com.task1.news.newVersion.ui.category
import com.task1.news.newVersion.ui.home.CategoriesFragment


class HomeActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var categories: CategoriesFragment
    lateinit var news: NewsFragment
    lateinit var ic_menu: ImageView
    lateinit var categoryName: TextView
    lateinit var adapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        pushFragment(categories)

        navigationView.setNavigationItemSelectedListener {

            if (it.itemId == R.id.categories) {

                pushFragment(CategoriesFragment())

            }

            return@setNavigationItemSelectedListener true
        }

        navigationView.setCheckedItem(R.id.categories)

        ic_menu.setOnClickListener {

            drawerLayout.open()
        }


        categories.onCategoryClick = object : CategoriesFragment.onCategoryClickListener {
            override fun onClick(position: Int, category: category) {
                pushFragment(NewsFragment.getInstance(category), true)


            }
        }
    }

    fun initViews(){

        navigationView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)
        ic_menu = findViewById(R.id.ic_menu)
        adapter = NewsAdapter(null)
        categoryName = findViewById(R.id.category_name)
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
        drawerLayout.close()
    }


}