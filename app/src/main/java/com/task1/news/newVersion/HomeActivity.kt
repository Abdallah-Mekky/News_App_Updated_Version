package com.task1.news.newVersion

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.task1.news.Model.ArticlesItem
import com.task1.news.NewsAdapter
import com.task1.news.R
import com.task1.news.newVersion.ui.NewsFragment
import com.task1.news.newVersion.ui.category
import com.task1.news.newVersion.ui.gallery.SettingsFragment
import com.task1.news.newVersion.ui.home.CategoriesFragment

class HomeActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var categories: CategoriesFragment
    lateinit var news: NewsFragment
    lateinit var ic_menu: ImageView
    lateinit var categoryName: TextView
    lateinit var search:androidx.appcompat.widget.SearchView
  //  lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigationView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)
        ic_menu = findViewById(R.id.ic_menu)



        categoryName = findViewById(R.id.category_name)

       /* search.setOnClickListener {

            categoryName.text = "kk"

        }*/

        categories = CategoriesFragment()
        news = NewsFragment()

        pushFragment(categories)

        navigationView.setNavigationItemSelectedListener {

            if (it.itemId == R.id.categories) {

                pushFragment(CategoriesFragment())

            } else if (it.itemId == R.id.settings) {

                pushFragment(SettingsFragment())

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



      //  search.isVisible = true

        search =  findViewById(R.id.SearchView)


         //if(javaClass == HomeActivity::class.java){
       // search?.isVisible = true }

        search.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@HomeActivity,"looking for $query",Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })


    }





    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {




        //val search = menu?.findItem(R.id.SearchView)
        val searchView:androidx.appcompat.widget.SearchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.queryHint = "abdjwoooooooooooo"
        searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()
                searchView.setQuery("",false)
                search.collapseActionView()
                Toast.makeText(this@HomeActivity,"looking for $query",Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })



        return true
    }*/

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

    /*override fun onQueryTextSubmit(query: String?): Boolean {
        Toast.makeText(this,"lllll",Toast.LENGTH_LONG).show()

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        Toast.makeText(this,"lllll",Toast.LENGTH_LONG).show()
return true
    }*/

}