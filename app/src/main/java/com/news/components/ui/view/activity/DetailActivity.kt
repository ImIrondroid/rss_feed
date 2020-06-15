package com.news.components.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.news.components.BR
import com.news.components.R
import com.news.components.databinding.ActivityDetailBinding
import com.news.components.model.News
import com.news.components.ui.adapter.KeywordAdapter

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent?.getSerializableExtra("data") as News
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.run {
            setVariable(BR.news, data)
            rcvKeyword.adapter = KeywordAdapter(data.keyWords).apply {
                limitedItemCount = 3
            }
        }
    }
}
