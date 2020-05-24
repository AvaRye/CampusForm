package com.example.campusform

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
class SingleItem(val context: Context) : Item {

    override fun areItemsTheSame(newItem: Item): Boolean {
        return newItem is SingleItem
    }

    companion object Controller : ItemController {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is SingleViewHolder && item is SingleItem) {
                holder.questionContainer.apply {
                    getChildAt(0).tv_question_number.text = "${position + 1}."
                    getChildAt(0).et_question_content.hint = "题目内容"
                    getChildAt(1).tv_question_number.text = "A."
                    getChildAt(1).et_question_content.hint = "选项内容"

                    this.addView(createChildView(this, context))
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_new_questions_single_select, parent, false)
            return SingleViewHolder(
                view,
                view.cb_item_single_select,
                view.ll_questions_single_container
            )
        }

    }

    class SingleViewHolder(itemView: View, val cb: CheckBox, val questionContainer: LinearLayout) :
        QuestionViewHolder(itemView)

    override val controller: ItemController
        get() = Controller

}

class MultiItem(val context: Context) : Item {
    override fun areItemsTheSame(newItem: Item): Boolean {
        return newItem is MultiItem
    }

    companion object Controller : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
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

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is MultiViewHolder && item is MultiItem) {
                holder.questionContainer.apply {
                    getChildAt(0).tv_question_number.text = "${position + 1}."
                    getChildAt(0).et_question_content.hint = "题目内容..."
                    getChildAt(1).tv_question_number.text = "A."
                    getChildAt(1).et_question_content.hint = "选项内容..."

                    this.addView(createChildView(this, context))
                }
                val view =
                    LayoutInflater.from(item.context).inflate(R.layout.popup_multi_setting, null)

                val popup = PopupWindow(view)
                popup.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                popup.isOutsideTouchable = true
                holder.setting.setOnClickListener {
                    Toast.makeText(item.context, "多选-设置", Toast.LENGTH_SHORT).show()
                    popup.showAsDropDown(it)
                }
            }
        }

    }

    class MultiViewHolder(
        itemView: View,
        val cb: CheckBox,
        val questionContainer: LinearLayout,
        val setting: ImageView
    ) : QuestionViewHolder(itemView)

    override val controller: ItemController
        get() = Controller
}

class TextItem(val context: Context) : Item {
    override fun areItemsTheSame(newItem: Item): Boolean {
        return newItem is TextItem
    }

    override val controller: ItemController
        get() = Controller

    companion object Controller : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
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

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is TextViewHolder && item is TextItem) {
                holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->

                }
                holder.qNum.text = "${position + 1}."
                holder.qTitle.hint = "题目内容..."
            }
        }
    }

    class TextViewHolder(
        itemView: View,
        val cb: CheckBox,
        val qNum: TextView,
        val qTitle: EditText
    ) :
        QuestionViewHolder(itemView)

}

class TenItem(val context: Context) : Item {
    override fun areItemsTheSame(newItem: Item): Boolean {
        return newItem is TenItem
    }

    override val controller: ItemController
        get() = Controller

    companion object Controller : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
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

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is TenViewHolder && item is TenItem) {
                holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->

                }
                holder.qNum.text = "${position + 1}."
                holder.qTitle.hint = "题目内容..."
                holder.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    when (checkedId) {
                        R.id.rb_1 -> {
                            Toast.makeText(item.context, "1", Toast.LENGTH_SHORT).show()
                        }
                        R.id.rb_2 -> {
                            Toast.makeText(item.context, "2", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    class TenViewHolder(
        itemView: View,
        val cb: CheckBox,
        val qNum: TextView,
        val qTitle: TextView,
        val etLow: EditText,
        val etHigh: EditText,
        val radioGroup: RadioGroup
    ) : QuestionViewHolder(itemView)
}

class HundredItem(val context: Context) : Item {
    override fun areItemsTheSame(newItem: Item): Boolean {
        return newItem is HundredItem
    }

    override val controller: ItemController
        get() = Controller

    companion object Controller : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
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

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is HundredViewHolder && item is HundredItem) {
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
        }
    }

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

}

class SortItem(val context: Context) : Item {
    override fun areItemsTheSame(newItem: Item): Boolean {
        return newItem is SortItem
    }

    override val controller: ItemController
        get() = Controller

    companion object Controller : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_new_questions_sort, parent, false)
            return SortViewHolder(
                view,
                view.cb_item_sort_select,
                view.ll_questions_sort_container
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is SortViewHolder && item is SortItem) {
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

                    this.addView(createChildView(this, context))
                }
            }
        }
    }

    class SortViewHolder(
        itemView: View,
        val cb: CheckBox,
        val questionContainer: LinearLayout
    ) : QuestionViewHolder(itemView)
}

//
//class QuestionAdapter(private val context: Context, private val typeList: ArrayList<QuestionType>) :
//    RecyclerView.Adapter<QuestionViewHolder>() {
//    companion object {
//        enum class QuestionType {
//            SINGLE_QUESTION,
//            MULTI_QUESTION,
//            TEXT_QUESTION,
//            TEN_QUESTION,
//            HUND_QUESTION,
//            SORT_QUESTION
//        }
//    }
//
//    private val selectedQuestions = ArrayList<Int>()
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
//        when (viewType) {
//            3 -> {//十分题
//
//            }
//            4 -> {//百分题
//
//            }
//            5 -> {//排序题
//
//            }
//        }
//        val view = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.item_new_questions_single_select, parent, false)
//        return SingleViewHolder(
//            view,
//            view.cb_item_single_select,
//            view.ll_questions_single_container
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return typeList.size
//    }
//
//    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
//        when (holder) {
//            is TenViewHolder -> {
//
//            }
//            is HundredViewHolder -> {
//
//            }
//            is SortViewHolder -> {
//
//            }
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return when (typeList[position]) {
//            QuestionType.SINGLE_QUESTION -> 0
//            QuestionType.MULTI_QUESTION -> 1
//            QuestionType.TEXT_QUESTION -> 2
//            QuestionType.TEN_QUESTION -> 3
//            QuestionType.HUND_QUESTION -> 4
//            QuestionType.SORT_QUESTION -> 5
//        }
//    }
//
//    fun addQuestion(type: QuestionType) {
//        typeList.add(type)
//        notifyItemInserted(typeList.size - 1)
//    }
//
//    //    fun delete(){
////        for(position in selectedQuestions){
////            deleteQuestion(position)
////        }
////        notifyDataSetChanged()
////
////    }
//    private fun deleteQuestion(position: Int) {
//        typeList.removeAt(position)
//    }
//
//
//}

@Synchronized
fun createChildView(root: ViewGroup, context: Context): View {
    val view =
        LayoutInflater.from(context).inflate(R.layout.layout_question_item, root, false)
    view.tv_question_number.visibility = View.INVISIBLE
    view.iv_question_add.visibility = View.VISIBLE
    view.iv_question_sub.setOnClickListener {
        var position = root.indexOfChild(view)
        root.removeView(view)
        while (position < root.childCount) {
            val child = root.getChildAt(position)
            child.tv_question_number.text = 'A'.plus(position - 1) + "."
            position++
        }
    }
    view.iv_question_add.setOnClickListener {
        view.iv_question_sub.visibility = View.VISIBLE
        view.tv_question_number.text = 'A'.plus(root.childCount - 2) + "."
        root.addView(createChildView(root, context))
        it.visibility = View.INVISIBLE
        view.tv_question_number.visibility = View.VISIBLE
        view.et_question_content.hint = "选项内容..."
    }
    return view
}