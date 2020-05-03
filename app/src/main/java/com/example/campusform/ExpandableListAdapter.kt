package com.example.campusform

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import kotlinx.android.synthetic.main.item_list_child.view.*
import kotlinx.android.synthetic.main.item_list_group.view.*

class ExpandableListAdapter(context: Context, private val list: List<String>) :
    BaseExpandableListAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = inflater.inflate(R.layout.item_list_group, null)
        view.tv_list_group.text = list[groupPosition]
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = inflater.inflate(R.layout.item_list_child, null)
        view.tv_list_child_name.text = list[groupPosition]
        view.tv_list_child_time.text = list[groupPosition]
        return view
    }

    override fun getGroup(groupPosition: Int): Any {
        return list[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return list[groupPosition]
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return list.size
    }

    override fun getGroupCount(): Int {
        return list.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

}