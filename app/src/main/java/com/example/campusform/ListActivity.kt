package com.example.campusform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_list_child.view.*

class ListActivity : AppCompatActivity() {
    lateinit var listAdapter: ExpandableListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listAdapter = ExpandableListAdapter(this, mutableListOf())
        elv_list.apply {
            setGroupIndicator(null)
            setAdapter(listAdapter)
            tv_list_child_name.setOnClickListener {
                //跳转到预览
                Toast.makeText(this@ListActivity, "预览", Toast.LENGTH_SHORT).show()
            }
            iv_list_child_delete.setOnClickListener {
                deleteItem()
                refresh()
            }

        }

        fab_new.setOnClickListener {
            //跳转到编辑
            Toast.makeText(this, "新建", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteItem() {

    }

    private fun refresh() {

    }
}
