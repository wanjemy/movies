package com.movies.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movies.R

abstract class BaseRecyclerViewAdapter<ITEM : BaseCommonModel>(private val recyclerView: RecyclerView? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM = 0
        private const val LOADING = -1
        const val RETRY = -2
    }

    protected val recyclerviewItem = mutableListOf<ITEM>()
    private var isLoading = false

    interface BaseRecyclerViewAdapterListener {
        fun onLoadMore()
        fun onRetryButtonClick()
    }

    init {
        recyclerView?.let {
            val linearLayoutManager = it.layoutManager as LinearLayoutManager?
            linearLayoutManager?.let { manager ->
                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val lastVisibleItem = manager.findLastVisibleItemPosition()

                        if (lastVisibleItem > recyclerviewItem.size - 3 && !isLoading) {
                            onLoadMore()
                        }
                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOADING -> ItemLoading(
                LayoutInflater.from(parent.context).inflate(R.layout.rv_item_loading, parent, false)
            )
            RETRY -> ItemRetry(
                LayoutInflater.from(parent.context).inflate(R.layout.rv_item_retry, parent, false)
            )
            else -> generateViewHolder(parent, viewType)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return recyclerviewItem[position].recyclerViewViewType
    }

    override fun getItemCount(): Int {
        return recyclerviewItem.size
    }

    abstract fun generateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    protected open fun onClickButtonRetry() {
        removeRetryOrLoading()
        addLoadingHolder()
    }

    protected open fun onLoadMore() {
        isLoading = true
        addLoadingHolder()
    }

    open fun addNewData(newData: List<ITEM>) {
        if (recyclerviewItem.isNotEmpty() && isLoading) {
            removeRetryOrLoading()
        }

        if (newData.isNotEmpty()) {
            isLoading = false
            recyclerviewItem.addAll(newData)
            notifyNewItemInserted()
        }
    }

    open fun addNewData(newData: ITEM) {
        recyclerviewItem.add(newData)
        notifyNewItemInserted()
    }

    open fun addNewDataToTop(newData: ITEM) {
        recyclerviewItem.add(0, newData)
        notifyItemInserted(0)
    }

    @Suppress("UNCHECKED_CAST")
    fun addLoadingHolder() {
        addNewData(BaseCommonModel(LOADING) as ITEM)
    }

    @Suppress("UNCHECKED_CAST")
    fun addRetryHolder() {
        addNewData(BaseCommonModel(RETRY) as ITEM)
    }

    fun clearAllData() {
        if (recyclerviewItem.isNotEmpty()) {
            recyclerviewItem.clear()
            notifyDataSetChanged()
        }
    }

    private fun removeRetryOrLoading() {
        val positionToRemove = itemCount - 1

        if (recyclerviewItem[positionToRemove].recyclerViewViewType == LOADING ||
            recyclerviewItem[positionToRemove].recyclerViewViewType == RETRY
        ) {
            recyclerviewItem.removeAt(positionToRemove)
            notifyItemRemoved(positionToRemove)
        }
    }

    private fun notifyNewItemInserted() {
        if (recyclerView != null) {
            recyclerView.post {
                notifyItemInserted(itemCount)
            }
        } else {
            notifyItemInserted(itemCount)
        }
    }

    private inner class ItemLoading(view: View) : RecyclerView.ViewHolder(view)

    private inner class ItemRetry(view: View) : RecyclerView.ViewHolder(view) {
//        val bRetry: Button = view.findViewById(R.id.bRetry)
//
//        init {
//            bRetry.setOnClickListener {
//                onClickButtonRetry()
//            }
//        }
    }
}