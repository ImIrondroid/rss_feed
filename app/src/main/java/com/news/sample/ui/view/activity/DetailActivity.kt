package com.news.sample.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.news.sample.BR
import com.news.sample.R
import com.news.sample.databinding.ActivityDetailBinding
import com.news.sample.model.News
import com.news.sample.ui.adapter.KeywordAdapter

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent?.getSerializableExtra("data") as News
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.setVariable(BR.news, data)
        binding.rcvKeyword.adapter = KeywordAdapter(data.keyWords).apply {
            limitedItemCount = 3
        }
    }
}
