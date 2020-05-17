package com.example.campusform

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_new_questions_hundred.view.*
import kotlinx.android.synthetic.main.item_new_questions_multi_select.view.*
import kotlinx.android.synthetic.main.item_new_questions_single_select.view.*
import kotlinx.android.synthetic.main.item_new_questions_sort.view.*
import kotlinx.android.synthetic.main.item_new_questions_ten.view.*
import kotlinx.android.synthetic.main.item_new_questions_text.view.*
import kotlinx.android.synthetic.main.layout_question_item.view.*
import kotlinx.android.synthetic.main.layout_question_item_sort.view.et_question_content

open class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class SingleViewHolder(itemView: View, val cb: CheckBox, val questionContainer: LinearLayout) :
    QuestionViewHolder(itemView)

class MultiViewHolder(
    itemView: View,
    val cb: CheckBox,
    val questionContainer: LinearLayout,
    val setting: ImageView
) : QuestionViewHolder(itemView)

class TextViewHolder(itemView: View, val cb: CheckBox, val qNum: TextView, val qTitle: EditText) :
    QuestionViewHolder(itemView)

class TenViewHolder(
    itemView: View,
    val cb: CheckBox,
    val qNum: TextView,
    val qTitle: TextView,
    val etLow: EditText,
    val etHigh: EditText,
    val radioGroup: RadioGroup
) : QuestionViewHolder(itemView)

class HundredViewHolder(
    itemView: View,
    val cb: CheckBox,
    val qNum: TextView,
    val qTitle: TextView,
    val etLow: EditText,
    val etHigh: EditText,
    val seekBar: SeekBar,
    val tvPercent: TextView
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
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_new_questions_multi_select, parent, false)
                return MultiViewHolder(
                    view,
                    view.cb_item_multi_select,
                    view.ll_questions_multi_container,
                    view.iv_item_multi_setting
                )
            }
            2 -> {//文本题
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_new_questions_text, parent, false)
                return TextViewHolder(
                    view,
                    view.cb_item_text_select,
                    view.tv_question_text_number,
                    view.et_question_text_content

                )
            }
            3 -> {//十分题
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_new_questions_ten, parent, false)
                return TenViewHolder(
                    view,
                    view.cb_item_ten_select,
                    view.tv_question_ten_number,
                    view.et_question_ten_content,
                    view.et_question_low_ten,
                    view.et_question_high_ten,
                    view.rg_question_ten
                )
            }
            4 -> {//百分题
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_new_questions_hundred, parent, false)
                return HundredViewHolder(
                    view,
                    view.cb_item_hundred_select,
                    view.tv_question_hundred_number,
                    view.et_question_hundred_content,
                    view.et_question_low_hundred,
                    view.et_question_high_hundred,
                    view.sb_question_hundred,
                    view.tv_hundred_percent
                )
            }
            5 -> {//排序题
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_new_questions_sort, parent, false)
                return SortViewHolder(
                    view,
                    view.cb_item_sort_select,
                    view.ll_questions_sort_container
                )
            }
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_new_questions_single_select, parent, false)
        return SingleViewHolder(
            view,
            view.cb_item_single_select,
            view.ll_questions_single_container
        )
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        when (holder) {
            is SingleViewHolder -> {
                //            holder.cb.setOnCheckedChangeListener { _, isChecked ->
                //                if (isChecked) {
                //                    selectedQuestions.add(position)
                //                } else {
                //                    selectedQuestions.remove(position)
                //                }
                //            }
                holder.questionContainer.apply {
                    getChildAt(0).tv_question_number.text = "${position + 1}."
                    getChildAt(0).et_question_content.hint = "题目内容"
                    getChildAt(1).tv_question_number.text = "A."
                    getChildAt(1).et_question_content.hint = "选项内容"

                    this.addView(createChildView(this, 2))
                }
            }
            is MultiViewHolder -> {
                //            holder.cb.setOnCheckedChangeListener { _, isChecked ->
                //                if (isChecked) {
                //                    selectedQuestions.add(position)
                //                } else {
                //                    selectedQuestions.remove(position)
                //                }
                //            }
                holder.questionContainer.apply {
                    getChildAt(0).tv_question_number.text = "${position + 1}."
                    getChildAt(0).et_question_content.hint = "题目内容..."
                    getChildAt(1).tv_question_number.text = "A."
                    getChildAt(1).et_question_content.hint = "选项内容..."

                    this.addView(createChildView(this, 2))
                }
                val view =
                    LayoutInflater.from(context).inflate(R.layout.popup_multi_setting, null)

                val popup = PopupWindow(view)
                popup.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                popup.isOutsideTouchable = true
                holder.setting.setOnClickListener {
                    Toast.makeText(context, "多选-设置", Toast.LENGTH_SHORT).show()
                    popup.showAsDropDown(it)
                }
            }
            is TextViewHolder -> {
                holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->

                }
                holder.qNum.text = "${position + 1}."
                holder.qTitle.hint = "题目内容..."
            }
            is TenViewHolder -> {
                holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->

                }
                holder.qNum.text = "${position + 1}."
                holder.qTitle.hint = "题目内容..."
                holder.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    when (checkedId) {
                        R.id.rb_1 -> {
                            Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                        }
                        R.id.rb_2 -> {
                            Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            is HundredViewHolder -> {
                holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->

                }
                holder.qNum.text = "${position + 1}."
                holder.qTitle.hint = "题目内容..."
                holder.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        holder.tvPercent.text = progress.toString()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }

                })
            }
            is SortViewHolder -> {
                holder.cb.setOnCheckedChangeListener { _, isChecked ->
                    //                if (isChecked) {
                    //                    selectedQuestions.add(position)
                    //                } else {
                    //                    selectedQuestions.remove(position)
                    //                }
                    //            }

                }
                holder.questionContainer.apply {
                    getChildAt(0).tv_question_number.text = "${position + 1}."
                    getChildAt(0).et_question_content.hint = "题目内容..."
                    getChildAt(1).tv_question_number.text = "A."
                    getChildAt(1).et_question_content.hint = "选项内容..."

                    this.addView(createChildView(this, 2))
                }
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
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_question_item, root, false)
        view.tv_question_number.visibility = View.INVISIBLE
        view.iv_question_add.visibility = View.VISIBLE
        view.iv_question_add.setOnClickListener {
            root.addView(createChildView(root, position + 1))
            it.visibility = View.INVISIBLE
            view.tv_question_number.text = 'A'.plus(position - 1) + "."
            view.tv_question_number.visibility = View.VISIBLE
            view.et_question_content.hint = "选项内容..."
        }
        return view
    }
}