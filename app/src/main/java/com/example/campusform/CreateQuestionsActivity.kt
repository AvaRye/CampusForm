package com.example.campusform

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_new.*

class CreateQuestionsActivity : AppCompatActivity() {
    private var type = QuestionType.SINGLE_QUESTION
    private val itemList = arrayListOf<Item>()
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
                type = when (position) {
                    0 -> QuestionType.SINGLE_QUESTION
                    1 -> QuestionType.MULTI_QUESTION
                    2 -> QuestionType.TEXT_QUESTION
                    3 -> QuestionType.TEN_QUESTION
                    4 -> QuestionType.HUNDRED_QUESTION
                    5 -> QuestionType.SORT_QUESTION
                    else -> {
                        QuestionType.SINGLE_QUESTION
                    }
                }
            }
        }
//        adapter = QuestionAdapter(
//            this,
//            arrayListOf()
//        )
//        adapter.addQuestion(QuestionAdapter.Companion.QuestionType.SINGLE_QUESTION)
//        rv_new_questions.adapter = adapter
        itemList.add(ItemFactory.createItem(type,this))

        rv_new_questions.adapter = ItemAdapter(ItemManager())
        rv_new_questions.layoutManager = LinearLayoutManager(this)
        rv_new_questions.refreshAll {
            this.addAll(itemList)
        }
        iv_new_add.setOnClickListener {
            itemList.add(ItemFactory.createItem(type,this))
            rv_new_questions.refreshAll {
                this.addAll(itemList)
            }
        }
        iv_new_remove.setOnClickListener {
//            adapter.delete()
        }
    }
}