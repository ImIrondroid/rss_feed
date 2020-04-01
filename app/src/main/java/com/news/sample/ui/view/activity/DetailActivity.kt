package com.news.sample.ui.view.activity

import android.os.Bundle
import android.util.Log
import com.news.sample.BR
import com.news.sample.R
import com.news.sample.base.BaseActivity
import com.news.sample.databinding.ActivityDetailBinding
import com.news.sample.model.News
import com.news.sample.ui.adapter.KeywordAdapter
import com.news.sample.ui.navigator.DetailNavigator
import com.news.sample.ui.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.NullPointerException

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(), DetailNavigator {

    private val mViewModel: DetailViewModel by viewModel()

    override val layoutResId: Int
        get() = R.layout.activity_detail

    override val viewModel: DetailViewModel
        get() = mViewModel

    override val data: News
        get() = intent.extras?.getSerializable("data") as News

    override fun registerNavigator() =
        viewModel.setNavigator(this)

    override fun setBindingVariables() {
        super.setBindingVariables()
        viewDataBinding.setVariable(BR.news, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.rcvKeyword.adapter = KeywordAdapter(data.keyWords).apply {
            limitedItemCount = 3
        }
    }
}
