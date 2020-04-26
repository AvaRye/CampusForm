package com.example.campusform

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*

class CreateActivity : AppCompatActivity() {
    private val arrayList = arrayListOf("单选题", "多选题", "文本题", "十分量表题", "百分量表题", "排序题")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        iv_new_back.setOnClickListener {
            onBackPressed()
        }
        btn_new_submit.setOnClickListener {

        }
        spinner_new_type.adapter =
            ArrayAdapter<String>(this, R.layout.item_new_spinner, arrayList).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        spinner_new_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }

    }
}