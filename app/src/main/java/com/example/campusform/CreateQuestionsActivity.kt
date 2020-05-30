package com.example.campusform

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.item_toolbar.view.*
import java.util.*
import kotlin.math.min

class CreateQuestionsActivity : AppCompatActivity() {
    private var type = QuestionAdapter.Companion.QuestionType.SINGLE_QUESTION
    private lateinit var adapter: QuestionAdapter
    private val arrayList = arrayListOf("单选题", "多选题", "文本题", "十分量表题", "百分量表题", "排序题")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val calendar = Calendar.getInstance()
        var mYear = calendar.get(Calendar.YEAR)
        var mMonth = calendar.get(Calendar.MONTH)
        var mDay = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$mYear-" + String.format("%02d", mMonth + 1) + "-" + String.format(
            "%02d",
            mDay
        )
        val time = "00:00"

        tb_new.apply {
            tv_tb_title.text = getString(R.string.edit_question)
            iv_tb_back.visibility = View.VISIBLE
            iv_tb_avatar.visibility = View.VISIBLE
            iv_tb_back.setOnClickListener {
                onBackPressed()
            }
        }

        val dateBeginDialog = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                mYear = year
                mMonth = month
                mDay = dayOfMonth
                val date =
                    "$year-" + String.format("%02d", month + 1) + "-" + String.format(
                        "%02d",
                        dayOfMonth
                    )
                tv_new_date_begin.text = date
            },
            mYear,
            mMonth,
            mDay
        )

        val dateEndDialog = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val date =
                    "$year-" + String.format("%02d", month + 1) + "-" + String.format(
                        "%02d",
                        dayOfMonth
                    )
                tv_new_date_end.text = date
            },
            mYear,
            mMonth,
            mDay
        )

        val timeBeginDialog = TimePickerDialog(
            this, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val timeNew = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute)
                tv_new_time_begin.text = timeNew
            }, 0, 0, true
        )

        val timeEndDialog = TimePickerDialog(
            this, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val timeNew = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute)
                tv_new_time_end.text = timeNew
            }, 0, 0, true
        )

        tv_new_date_begin.apply {
            text = date
            setOnClickListener {
                dateBeginDialog.show()
            }
        }
        tv_new_date_end.apply {
            text = date
            setOnClickListener {
                dateEndDialog.show()
            }
        }
        tv_new_time_begin.apply {
            text = time
            setOnClickListener {
                timeBeginDialog.show()
            }
        }
        tv_new_time_end.apply {
            text = time
            setOnClickListener {
                timeEndDialog.show()
            }
        }

        tv_new_submit.setOnClickListener {
            //submit
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
                    0 -> QuestionAdapter.Companion.QuestionType.SINGLE_QUESTION
                    1 -> QuestionAdapter.Companion.QuestionType.MULTI_QUESTION
                    2 -> QuestionAdapter.Companion.QuestionType.TEXT_QUESTION
                    3 -> QuestionAdapter.Companion.QuestionType.TEN_QUESTION
                    4 -> QuestionAdapter.Companion.QuestionType.HUND_QUESTION
                    5 -> QuestionAdapter.Companion.QuestionType.SORT_QUESTION
                    else -> {
                        QuestionAdapter.Companion.QuestionType.SINGLE_QUESTION
                    }
                }
            }
        }
        adapter = QuestionAdapter(
            this,
            arrayListOf()
        )
        adapter.addQuestion(QuestionAdapter.Companion.QuestionType.SINGLE_QUESTION)
        rv_new_questions.adapter = adapter
        rv_new_questions.layoutManager = LinearLayoutManager(this)
        iv_new_add.setOnClickListener {
            adapter.addQuestion(type)
        }
        iv_new_remove.setOnClickListener {
//            adapter.delete()
        }
    }

    fun submit() {

    }
}