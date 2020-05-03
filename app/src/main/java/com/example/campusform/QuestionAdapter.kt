package com.example.campusform

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_new_questions_single_select.view.*
import kotlinx.android.synthetic.main.layout_question_item.view.*

open class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class SingleViewHolder(itemView: View, val cb: CheckBox, val questionContainer: LinearLayout) :
    QuestionViewHolder(itemView)

class MutliViewHolder(
    itemView: View,
    val cb: CheckBox,
    val questionContainer: LinearLayout,
    val setting: ImageView
) : QuestionViewHolder(itemView)

class TextViewHolder(itemView: View, val cb: CheckBox, val qNum: Int, val qTitle: TextView) :
    QuestionViewHolder(itemView)

class TenViewHolder(
    itemView: View,
    val cb: CheckBox,
    val qNum: Int,
    val qTitle: TextView,
    val etLow: EditText,
    val etHigh: EditText,
    val radioGroup: RadioGroup
) : QuestionViewHolder(itemView)

class HundViewHolder(
    itemView: View,
    val cb: CheckBox,
    val etLow: EditText,
    val etHigh: EditText,
    val seekBar: SeekBar
) : QuestionViewHolder(itemView)

class SortViewHolder(
    itemView: View,
    val cb: CheckBox,
    val questionContainer: LinearLayout
) : QuestionViewHolder(itemView)

class QuestionAdapter(private val context: Context, private val typeList: ArrayList<QuestionType>) :
    RecyclerView.Adapter<QuestionViewHolder>() {
    companion object {
        enum class QuestionType {
            SINGLE_QUESTION,
            MULTI_QUESTION,
            TEXT_QUESTION,
            TEN_QUESTION,
            HUND_QUESTION,
            SORT_QUESTION
        }
    }

    private val selectedQuestions = ArrayList<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        when (viewType) {
            0 -> {
                //单选题
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_new_questions_single_select, parent, false)
                return SingleViewHolder(
                    view,
                    view.cb_item_single_select,
                    view.ll_questions_single_container
                )
            }
            1 -> {//多选题

            }
            2 -> {//文本题

            }
            3 -> {//十分题

            }
            4 -> {//百分题

            }
            5 -> {//排序题

            }
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_new_questions_single_select, parent, false)
        return SingleViewHolder(view, view.cb_item_single_select, view.ll_questions_single_container)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        if (holder is SingleViewHolder) {
            holder.cb.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedQuestions.add(position)
                } else {
                    selectedQuestions.remove(position)
                }
            }
            holder.questionContainer.apply {
                getChildAt(0).tv_question_number.text = "${position + 1}."
                getChildAt(0).et_question_content.hint = "题目内容"
                getChildAt(1).tv_question_number.text = "A."
                getChildAt(1).et_question_content.hint = "选项内容"

                this.addView(createChildView(this, 2))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (typeList[position]) {
            QuestionType.SINGLE_QUESTION -> 0
            QuestionType.MULTI_QUESTION -> 1
            QuestionType.TEXT_QUESTION -> 2
            QuestionType.TEN_QUESTION -> 3
            QuestionType.HUND_QUESTION -> 4
            QuestionType.SORT_QUESTION -> 5
        }
    }

    fun addQuestion(type: QuestionType) {
        typeList.add(type)
        notifyItemInserted(typeList.size - 1)
    }

    //    fun delete(){
//        for(position in selectedQuestions){
//            deleteQuestion(position)
//        }
//        notifyDataSetChanged()
//
//    }
    private fun deleteQuestion(position: Int) {
        typeList.removeAt(position)
    }

    private fun createChildView(root: ViewGroup, position: Int): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_question_item, root, false)
        view.tv_question_number.visibility = View.INVISIBLE
        view.iv_question_add.visibility = View.VISIBLE
        view.iv_question_add.setOnClickListener {
            root.addView(createChildView(root, position + 1))
            it.visibility = View.INVISIBLE
            view.tv_question_number.text = 'A'.plus(position - 1) + "."
            view.tv_question_number.visibility = View.VISIBLE
            view.et_question_content.hint = "选项内容"
        }
        return view
    }
}