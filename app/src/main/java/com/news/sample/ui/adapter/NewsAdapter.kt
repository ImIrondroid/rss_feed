package com.news.sample.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.news.sample.R
import com.news.sample.model.News
import com.news.sample.util.ErrorImage
import com.news.sample.util.OnItemSelectedListener
import com.news.sample.util.extension.extract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    lateinit var context: Context
    lateinit var requestManager: RequestManager
    private var onItemSelectedListener : OnItemSelectedListener<News>? = null
    private var listDiffer : AsyncListDiffer<News> = AsyncListDiffer(this, object: DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.link==newItem.link
        }
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.link==newItem.link &&
                    oldItem.title==newItem.title &&
                    oldItem.description==newItem.description &&
                    oldItem.image==newItem.image &&
                    oldItem.keyWords==newItem.keyWords
        }
    })

    constructor(newsList: List<News>, requestManager: RequestManager) : this() {
        this.requestManager = requestManager
        listDiffer.submitList(newsList)
    }

    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener<News>) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    override fun getItemId(position: Int): Long = listDiffer.currentList[position].hashCode().toLong()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = listDiffer.currentList[position]

        if(onItemSelectedListener!=null) {
            holder.itemView.setOnClickListener {
                onItemSelectedListener?.invoke(item)
            }
        }

        if (!item.status) {
            doAsync {
                var doc: Document? = null

                //Exception 발생시 Retry
                for (times in 0 until NETWORK_TIMES) {
                    try {
                        doc = Jsoup.connect(item.link).timeout(LIMIT_TIME_OUT).get()
                        break
                    } catch (e: Exception) {
                        if (times == LIMIT_TIMES) e.printStackTrace()
                    }
                }
                if (doc == null) {
                    context.runOnUiThread {
                        requestManager
                            .load(ErrorImage.getDefaultImage())
                            .into(holder.image)
                    }
                } else {
                    item.status = true
                    val description: String =
                        doc.select("meta[property=og:description]").attr("content")
                    val image: String = doc.select("meta[property=og:image]").attr("content")
                    val keyWords = description.extract()
                    val keyWordsStringOnly = arrayListOf<String>()
                    keyWords.forEach { pair: Pair<String, Int> -> keyWordsStringOnly.add(pair.first) }

                    context.runOnUiThread {
                        item.image = image
                        item.description = description
                        item.keyWords = keyWordsStringOnly
                        notifyItemChanged(position)
                    }
                }
            }
        } else {
            doAsync {
                context.runOnUiThread {
                    holder.title.text = item.title
                    holder.description.text = item.description
                    requestManager
                        .load(item.image)
                        .thumbnail(0.5f)
                        .override(150, 150)
                        .error(ErrorImage.getDefaultImage())
                        .into(holder.image)
                    if (item.keyWords.isNullOrEmpty()) {
                        holder.rcvKeyword.visibility = View.INVISIBLE
                    } else {
                        holder.rcvKeyword.visibility = View.VISIBLE
                    }
                    holder.rcvKeyword.adapter = KeywordAdapter(item.keyWords).apply {
                        limitedItemCount = 3
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id._title)
        val description: TextView = view.findViewById(R.id._description)
        val image: ImageView = view.findViewById(R.id._image)
        val rcvKeyword: RecyclerView = view.findViewById(R.id._rcv_keyword)
    }

    companion object {
        const val LIMIT_TIME_OUT = 3000
        const val NETWORK_TIMES = 2
        const val LIMIT_TIMES = 1
    }
}