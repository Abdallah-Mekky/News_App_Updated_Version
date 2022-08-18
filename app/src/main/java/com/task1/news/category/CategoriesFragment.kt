package com.task1.news.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.task1.news.R
import com.task1.news.databinding.FragmentCategoriesBinding


class CategoriesFragment() : Fragment() {

    lateinit var category: List<Category>
    lateinit var categoriesViewModel: CategoriesViewModel
    lateinit var fragmentCategoriesBinding: FragmentCategoriesBinding
    var categories_Adapter: CategoryAdapter = CategoryAdapter(null)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentCategoriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        return fragmentCategoriesBinding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoriesViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        categoriesViewModel.createCategoryList()
        initViews()


    }

    private fun initViews() {

        fragmentCategoriesBinding.categoriesRecyclerView.adapter = categories_Adapter

        categories_Adapter.onItemClickListener = object : CategoryAdapter.OnItemClickListener {

            override fun onClick(position: Int, Category: Category) {
                onCategoryClick?.onClick(position, Category)
            }

        }
    }

    fun subscribeToLiveData() {

        categoriesViewModel.categoryLiveData.observe(viewLifecycleOwner, Observer { categoryList ->
            category = categoryList
            categories_Adapter.refreashAdapter(category)
        })
    }


    //Callback
    var onCategoryClick: onCategoryClickListener? = null

    interface onCategoryClickListener {

        fun onClick(position: Int, Category: Category)
    }

}