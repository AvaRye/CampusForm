package com.example.campusform

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campusform.commons.Item
import com.example.campusform.commons.ItemController
import kotlinx.android.synthetic.main.item_list_child.view.*
import org.jetbrains.anko.layoutInflater

class ListItem: Item {
    internal var builder: (ViewHolder.() -> Unit)? = null
    companion object Controller :
        ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view =
                parent.context.layoutInflater.inflate(R.layout.item_list_child, parent, false)
            val name = view.tv_list_child_name
            val time = view.tv_list_child_time
            return ViewHolder(view, name, time)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {
            holder as ViewHolder
            item as ListItem
            item.builder?.invoke(holder)
        }

        internal class ViewHolder(
            itemView: View,
            val name: TextView,
            val time: TextView
        ) : RecyclerView.ViewHolder(itemView)
    }

    override val controller: ItemController
        get() = ListItem
}
internal fun MutableList<Item>.listItem(builder: ListItem.Controller.ViewHolder.() -> Unit) =
    add(ListItem().apply { this.builder = builder })