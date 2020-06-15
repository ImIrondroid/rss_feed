package com.news.components.ui.view.activity

import android.content.Intent
import android.os.Bundle
import com.news.components.BR
import com.news.components.R
import com.news.components.base.BaseActivity
import com.news.components.databinding.ActivityMainBinding
import com.news.components.model.News
import com.news.components.ui.adapter.PositiveNewsAdapter
import com.news.components.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModel()
    private val newsAdapter: PositiveNewsAdapter by inject()

    override val layoutResId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        //start
        mainViewModel.work()
    }

    private fun init() {
        viewDataBinding.setVariable(BR.adapter, newsAdapter.apply {
            setOnItemSelectedListener { item: News ->
                startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply { putExtra("data", item) })
            }
        })
        swipe.setOnRefreshListener {
            mainViewModel.startRefresh()
        }
    }
}
