package com.task1.news.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.task1.news.R
import com.task1.news.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment() : Fragment() {

    private val categoriesViewModel: CategoriesViewModel by viewModels()
    @Inject lateinit var categoriesAdapter: CategoryAdapter
    lateinit var category: List<Category>
    lateinit var fragmentCategoriesBinding: FragmentCategoriesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentCategoriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        return fragmentCategoriesBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        categoriesViewModel.createCategoryList()
        initViews()
    }

    private fun initViews() {

        fragmentCategoriesBinding.categoriesRecyclerView.adapter = categoriesAdapter

        categoriesAdapter.onItemClickListener = object : CategoryAdapter.OnItemClickListener {

            override fun onClick(position: Int, Category: Category) {
                onCategoryClick?.onClick(position, Category)
            }
        }
    }

    private fun subscribeToLiveData() {

        categoriesViewModel.categoryLiveData.observe(viewLifecycleOwner, Observer { categoryList ->
            category = categoryList
            categoriesAdapter.refreashAdapter(category)
        })
    }


    //Callback
    var onCategoryClick: OnCategoryClickListener? = null

    interface OnCategoryClickListener {

        fun onClick(position: Int, Category: Category)
    }
}