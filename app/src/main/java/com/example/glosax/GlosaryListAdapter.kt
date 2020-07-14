package com.example.glosax

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_list_item.view.*
import kotlinx.android.synthetic.main.list_glosary_item.view.*

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class GlosaryListAdapter : ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> CategoryItemViewholder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.category_list_item, parent, false)
            )
            ITEM_VIEW_TYPE_ITEM -> ItemViewholder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_glosary_item, parent, false)
            )
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    fun addHeaderAndSumbitList(list: List<Glosary>?) {
        //TODO: Add Coroutines to handle loading of multiple headers in large data sets
        val items = when (list) {
            null -> listOf(DataItem.Header("Loading"))
            else -> {
                val groupedList = list.groupBy { it.category }
                var myList = ArrayList<DataItem>()

                for (i in groupedList.keys) {
                    myList.add(DataItem.Header(i))
                    for (v in groupedList.getValue(i)) {
                        myList.add(DataItem.ProductItem(v))
                    }
                }
                myList
            }
        }
        submitList(items)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewholder -> {
                val productItem = getItem(position) as DataItem.ProductItem
                holder.bind(productItem.product)
            }
            is CategoryItemViewholder -> {
                val headerItem = getItem(position) as DataItem.Header
                holder.bind(headerItem.CategoryName)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ProductItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Glosary) = with(itemView) {
            textCourse?.text = item.name
            textTitle?.text = item.price

        }
    }

    class CategoryItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) = with(itemView) {
            category?.text = item


        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

sealed class DataItem {
    data class ProductItem(val product: Glosary) : DataItem() {
        override val id = product.id
    }

    data class Header(val CategoryName: String) : DataItem() {
        override val id = CategoryName.hashCode().toLong()
    }

    abstract val id: Long
}