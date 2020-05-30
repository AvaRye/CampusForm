package com.example.campusform.question

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusform.Item
import com.example.campusform.ItemAdapter
import com.example.campusform.ItemManager
import com.example.campusform.R
import com.example.campusform.question.QuestionData.itemSelectedList
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.item_toolbar.view.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange

class CreateQuestionsActivity : AppCompatActivity() {
    private var type = QuestionType.SINGLE_QUESTION
    private lateinit var itemManager: ItemManager

    private val arrayList = arrayListOf("单选题", "多选题", "文本题", "十分量表题", "百分量表题", "排序题")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        tb_new.iv_tb_back.setOnClickListener {
            onBackPressed()
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
        itemManager = ItemManager()
        rv_new_questions.adapter = ItemAdapter(itemManager)
        rv_new_questions.layoutManager = LinearLayoutManager(this)

        addQuestion()
        iv_new_add.setOnClickListener {
            addQuestion()
        }
        iv_new_remove.setOnClickListener {
            val itemList = arrayListOf<Item>()
            val questionList = arrayListOf<Question>()
            val snapshot = itemManager.itemListSnapshot
            for (position in itemSelectedList) {
                itemList.add(snapshot[position])
                questionList.add(QuestionData.getQuestion(position))
            }
            for (item in itemList) {
                itemManager.remove(item)
            }
            for (question in questionList) {
                QuestionData.removeQuestion(question)
            }
            itemSelectedList.clear()
        }
        cb_new_all.onCheckedChange { buttonView, isChecked ->
            if (isChecked) {
                val snapshot = itemManager.itemListSnapshot
                itemSelectedList.clear()
                for (item in snapshot.withIndex()) {
                    val it = item.value
                    if (it is Checkable) {
                        it.check(isChecked)
                    }
                    itemSelectedList.add(item.index)
                }

            } else {
                val snapshot = itemManager.itemListSnapshot
                for (item in snapshot) {
                    if (item is Checkable) {
                        item.check(isChecked)
                    }
                }
                itemSelectedList.clear()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        QuestionData.clear()
    }

    private fun addQuestion() {
        QuestionData.addQuestion()
        when (type) {
            QuestionType.SINGLE_QUESTION, QuestionType.MULTI_QUESTION, QuestionType.SORT_QUESTION -> {
                QuestionData.addChoice(itemManager.size)
            }
        }
        itemManager.add(ItemFactory.createItem(type, this))

    }
}