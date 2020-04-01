package com.news.sample.ui.view.activity

import com.news.sample.R
import com.news.sample.base.BaseActivity
import com.news.sample.databinding.ActivitySplashBinding
import com.news.sample.ui.navigator.SplashNavigator
import com.news.sample.ui.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    private val mViewModel: SplashViewModel by viewModel()

    override val layoutResId: Int
        get() = R.layout.activity_splash

    override val viewModel: SplashViewModel
        get() = mViewModel

    override fun registerNavigator() =
        viewModel.setNavigator(this)

    override fun onResume() {
        super.onResume()
        viewModel.toMain()
    }
}
