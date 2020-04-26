package com.example.campusform

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemController

class SingleSelect() :Item{

    companion object Controller:ItemController{
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {

        }

        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater.from()
        }

    }
    override val controller = Controller
}