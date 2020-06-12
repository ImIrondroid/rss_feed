package com.iron.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    protected val context : Context
        get() = itemView.context

    open fun onBind(item : T?) {
        viewDataBinding.apply {
            setVariable(BR.item, item)
            executePendingBindings()
        }
    }
}