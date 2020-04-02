package com.news.sample.ui.view.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.news.sample.R
import com.news.sample.base.BaseActivity
import com.news.sample.databinding.ActivityMainBinding
import com.news.sample.ui.adapter.NewsAdapter
import com.news.sample.ui.navigator.MainNavigator
import com.news.sample.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private val mViewModel: MainViewModel by viewModel()

    override val layoutResId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = mViewModel

    override fun registerNavigator() =
        viewModel.setNavigator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.crawlingGoogle()
    }

    override fun crawlingLink() {
        viewDataBinding.rcvNews.apply {
            adapter = NewsAdapter(viewModel.linkList, Glide.with(this)).apply {
                setHasStableIds(true)
                setOnItemSelectedListener {
                    nextActivity(
                        kClass = DetailActivity::class,
                        bundle = Bundle().apply { putSerializable("data", it)
                        }
                    )
                }
            }
        }
    }
}
