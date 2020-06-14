package com.news.sample.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.news.sample.BR
import com.news.sample.ui.view.fragment.LoadingDialogFragment
import com.news.sample.util.ActionStore
import com.news.sample.util.ExceptionHandler
import kotlin.reflect.KClass

abstract class BaseActivity<VDB : ViewDataBinding, VM: BaseViewModel>
    : AppCompatActivity(), ActionStore {

    @IdRes
    protected open val containerResId: Int = 0

    @LayoutRes
    protected open val layoutResId: Int = 0
    protected open val viewModelId: Int = BR.viewModel

    protected abstract val viewModel: VM
    protected lateinit var viewDataBinding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(this))
        bindView()
    }

    private fun bindView() {
        viewDataBinding = DataBindingUtil.setContentView<VDB>(this, layoutResId)
        viewDataBinding.apply {
            setVariable(viewModelId, viewModel)
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }
    }

    override fun <T : Activity> nextActivity(kClass: KClass<T>, bundle: Bundle?, clearTask: Boolean) {
        startActivity(Intent(this, kClass.java).apply {
            bundle?.let(this::putExtras)
            if(clearTask) {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }
        })
    }

    override fun showLoading() {
        val existFragment  = supportFragmentManager
            .findFragmentByTag(LoadingDialogFragment.TAG) as? DialogFragment

        if(existFragment == null) {
            LoadingDialogFragment.instantiate()
                .show(supportFragmentManager, LoadingDialogFragment.TAG)
        } else {
            if(!existFragment.showsDialog) {
                existFragment
                    .show(supportFragmentManager, LoadingDialogFragment.TAG)
            }
        }
    }

    override fun hideLoading() {
        val existFragment = supportFragmentManager
            .findFragmentByTag(LoadingDialogFragment.TAG) as? LoadingDialogFragment
            ?: return

        if(existFragment.showsDialog) {
            existFragment.dismiss()
        }
    }
}