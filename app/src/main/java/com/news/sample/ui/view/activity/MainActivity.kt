package com.news.sample.ui.view.activity

import android.content.Intent
import android.os.Bundle
import com.news.sample.BR
import com.news.sample.R
import com.news.sample.base.BaseActivity
import com.news.sample.databinding.ActivityMainBinding
import com.news.sample.model.News
import com.news.sample.ui.adapter.PositiveNewsAdapter
import com.news.sample.ui.viewmodel.MainViewModel
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
        viewDataBinding.setVariable(BR.adapter, newsAdapter.apply {
            setOnItemSelectedListener { item: News ->
                startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply { putExtra("data", item) })
            }
        })
    }
}
