package com.example.campusform.question

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.campusform.Item
import com.example.campusform.ItemController
import com.example.campusform.R
import kotlinx.android.synthetic.main.item_new_questions_hundred.view.*
import kotlinx.android.synthetic.main.item_new_questions_multi_select.view.*
import kotlinx.android.synthetic.main.item_new_questions_single_select.view.*
import kotlinx.android.synthetic.main.item_new_questions_sort.view.*
import kotlinx.android.synthetic.main.item_new_questions_ten.view.*
import kotlinx.android.synthetic.main.item_new_questions_text.view.*
import kotlinx.android.synthetic.main.layout_question_item.view.*
import kotlinx.android.synthetic.main.layout_question_item_sort.view.et_question_content

open class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class SingleItem(val context: Context) : Item, Checkable {

//    override fun areItemsTheSame(newItem: Item): Boolean {
//        return newItem is SingleItem
//    }

    companion object Controller : ItemController, Checkable {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is SingleViewHolder && item is SingleItem) {
                if (!holders.contains(holder)) {
                    holders.add(holder)
                }
                val question = QuestionData.getQuestion(position)
                question.title?.apply {
                    holder.qTitle.text = SpannableStringBuilder(this)
                }
                holder.qTitle.hint = "题目内容.."
                holder.qTitle.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        question.title = p0.toString()
                        QuestionData.updateQuestion(position,question)
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                })
                holder.qNum.text = "${position + 1}."
                holder.questionContainer.apply {
                    this.removeAllViews()
                    //添加选项
                    while (childCount < question.choices.size) {
                        addView(createChoiceView(this, context,position))
                    }
                    addView(createChildView(this, context, position))
                    //设置数据
                    for (choice in question.choices.withIndex()) {
                        choice.value?.let {
                            getChildAt(choice.index).et_question_content.apply {
                                text = SpannableStringBuilder(it)
                                addTextChangedListener(object : TextWatcher {
                                    override fun afterTextChanged(p0: Editable?) {
                                        question.choices[choice.index] = p0.toString()
                                        QuestionData.updateQuestion(position,question)
                                    }

                                    override fun beforeTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {
                                    }

                                    override fun onTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {
                                    }

                                })
                            }
                        }
                    }
//                    val view = getChildAt(1)
//                    view.tv_question_number.visibility = View.INVISIBLE
//                    view.iv_question_add.visibility = View.VISIBLE
//                    view.iv_question_sub.setOnClickListener {
//                        var p = indexOfChild(view)
//                        removeView(view)
//                        while (p < childCount) {
//                            val child = getChildAt(p)
//                            child.tv_question_number.text = 'A'.plus(p) + "."
//                            p++
//                        }
//                    }
//                    view.iv_question_add.setOnClickListener {
//                        view.iv_question_sub.visibility = View.VISIBLE
//                        view.tv_question_number.text = 'A'.plus(childCount - 1) + "."
//                        addView(createChildView(this, context))
//                        it.visibility = View.INVISIBLE
//                        view.tv_question_number.visibility = View.VISIBLE
//                        view.et_question_content.hint = "选项内容..."
//                    }
//                    this.addView(createChildView(this, context))
                }

            }
            Log.d(
                "recycler",
                "onBindViewHolder position:$position itemId:${holder.itemId} \n$holder"
            )
        }

        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_new_questions_single_select, parent, false)
            Log.d("recycler", "onCreateViewHolder")
            return SingleViewHolder(
                view,
                view.cb_item_single_select,
                view.ll_questions_single_container,
                view.tv_question_single_number,
                view.et_question_single_content
            )
        }

        private val holders: ArrayList<SingleViewHolder> = ArrayList()

        override fun check(isChecked: Boolean) {
            for (holder in holders) {
                holder.cb.isChecked = isChecked
            }
        }


    }

    class SingleViewHolder(
        itemView: View,
        val cb: CheckBox,
        val questionContainer: LinearLayout,
        val qNum: TextView,
        val qTitle: EditText
    ) : QuestionViewHolder(itemView)

    override val controller: ItemController = Controller


    override fun check(isChecked: Boolean) {
        if (controller is Checkable) {
            controller.check(isChecked)
        }
    }

}

class MultiItem(val context: Context) : Item, Checkable {
//    override fun areItemsTheSame(newItem: Item): Boolean {
//        return newItem is MultiItem
//    }

    companion object Controller : ItemController, Checkable {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_new_questions_multi_select, parent, false)
            return MultiViewHolder(
                view,
                view.cb_item_multi_select,
                view.ll_questions_multi_container,
                view.iv_item_multi_setting,
                view.tv_question_multi_number,
                view.et_question_multi_content
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is MultiViewHolder && item is MultiItem) {
                if (!holders.contains(holder)) {
                    holders.add(holder)
                }
                holder.qNum.text = "${position + 1}."
                holder.qTitle.hint = "题目内容.."
                holder.questionContainer.apply {
                    getChildAt(0).tv_question_number.text = "A."
                    getChildAt(0).et_question_content.hint = "选项内容..."

                    val view = getChildAt(1)
                    view.tv_question_number.visibility = View.INVISIBLE
                    view.iv_question_add.visibility = View.VISIBLE
                    view.iv_question_sub.setOnClickListener {
                        var p = indexOfChild(view)
                        removeView(view)
                        while (p < childCount) {
                            val child = getChildAt(p)
                            child.tv_question_number.text = 'A'.plus(p) + "."
                            p++
                        }
                    }
                    view.iv_question_add.setOnClickListener {
                        view.iv_question_sub.visibility = View.VISIBLE
                        view.tv_question_number.text = 'A'.plus(childCount - 1) + "."
                        addView(createChildView(this, context, position))
                        it.visibility = View.INVISIBLE
                        view.tv_question_number.visibility = View.VISIBLE
                        view.et_question_content.hint = "选项内容..."
                    }
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

        private val holders: ArrayList<MultiViewHolder> = ArrayList()

        override fun check(isChecked: Boolean) {
            for (holder in holders) {
                holder.cb.isChecked = isChecked
            }
        }

    }

    class MultiViewHolder(
        itemView: View,
        val cb: CheckBox,
        val questionContainer: LinearLayout,
        val setting: ImageView,
        val qNum: TextView,
        val qTitle: EditText
    ) : QuestionViewHolder(itemView)

    override val controller: ItemController = Controller

    override fun check(isChecked: Boolean) {
        if (controller is Checkable) {
            controller.check(isChecked)
        }
    }
}

class TextItem(val context: Context) : Item, Checkable {
//    override fun areItemsTheSame(newItem: Item): Boolean {
//        return newItem is TextItem
//    }

    override val controller: ItemController = Controller

    override fun check(isChecked: Boolean) {
        if (controller is Checkable) {
            controller.check(isChecked)
        }
    }

    companion object Controller : ItemController, Checkable {
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
                if (!holders.contains(holder)) {
                    holders.add(holder)
                }
                holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->

                }
                holder.qNum.text = "${position + 1}."
                holder.qTitle.hint = "题目内容..."
            }
        }

        private val holders: ArrayList<TextViewHolder> = ArrayList()

        override fun check(isChecked: Boolean) {
            for (holder in holders) {
                holder.cb.isChecked = isChecked
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

class TenItem(val context: Context) : Item, Checkable {
    //    override fun areItemsTheSame(newItem: Item): Boolean {
//        return newItem is TenItem
//    }
    override val controller: ItemController = Controller

    override fun check(isChecked: Boolean) {
        if (controller is Checkable) {
            controller.check(isChecked)
        }
    }

    companion object Controller : ItemController, Checkable {
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
                if (!holders.contains(holder)) {
                    holders.add(holder)
                }
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

        private val holders: ArrayList<TenViewHolder> = ArrayList()

        override fun check(isChecked: Boolean) {
            for (holder in holders) {
                holder.cb.isChecked = isChecked
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

class HundredItem(val context: Context) : Item, Checkable {
//    override fun areItemsTheSame(newItem: Item): Boolean {
//        return newItem is HundredItem
//    }

    override val controller: ItemController = Controller

    override fun check(isChecked: Boolean) {
        if (controller is Checkable) {
            controller.check(isChecked)
        }
    }

    companion object Controller : ItemController, Checkable {
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
                if (!holders.contains(holder)) {
                    holders.add(holder)
                }
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

        private val holders: ArrayList<HundredViewHolder> = ArrayList()

        override fun check(isChecked: Boolean) {
            for (holder in holders) {
                holder.cb.isChecked = isChecked
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

class SortItem(val context: Context) : Item, Checkable {
//    override fun areItemsTheSame(newItem: Item): Boolean {
//        return newItem is SortItem
//    }


    companion object Controller : ItemController, Checkable {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_new_questions_sort, parent, false)
            return SortViewHolder(
                view,
                view.cb_item_sort_select,
                view.ll_questions_sort_container,
                view.tv_question_sort_number,
                view.et_question_sort_content
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item, position: Int) {
            if (holder is SortViewHolder && item is SortItem) {
                if (!holders.contains(holder)) {
                    holders.add(holder)
                }
                holder.cb.setOnCheckedChangeListener { _, isChecked ->

                }
                holder.qTitle.hint = "题目内容.."
                holder.qNum.text = "${position + 1}."
                holder.questionContainer.apply {

                    getChildAt(0).tv_question_number.text = "A."
                    getChildAt(0).et_question_content.hint = "选项内容..."

                    val view = getChildAt(1)
                    view.tv_question_number.visibility = View.INVISIBLE
                    view.iv_question_add.visibility = View.VISIBLE
                    view.iv_question_sub.setOnClickListener {
                        var p = indexOfChild(view)
                        removeView(view)
                        while (p < childCount) {
                            val child = getChildAt(p)
                            child.tv_question_number.text = 'A'.plus(p) + "."
                            p++
                        }
                    }
                    view.iv_question_add.setOnClickListener {
                        view.iv_question_sub.visibility = View.VISIBLE
                        view.tv_question_number.text = 'A'.plus(childCount - 1) + "."
                        addView(createChildView(this, context, position))
                        it.visibility = View.INVISIBLE
                        view.tv_question_number.visibility = View.VISIBLE
                        view.et_question_content.hint = "选项内容..."
                    }
                }
            }
        }

        private val holders: ArrayList<SortViewHolder> = ArrayList()

        override fun check(isChecked: Boolean) {
            for (holder in holders) {
                holder.cb.isChecked = isChecked
            }
        }

    }

    override val controller: ItemController = Controller

    override fun check(isChecked: Boolean) {
        if (controller is Checkable) {
            controller.check(isChecked)
        }
    }

    class SortViewHolder(
        itemView: View,
        val cb: CheckBox,
        val questionContainer: LinearLayout,
        val qNum: TextView,
        val qTitle: EditText
    ) : QuestionViewHolder(itemView)
}

enum class QuestionType {
    SINGLE_QUESTION,
    MULTI_QUESTION,
    TEXT_QUESTION,
    TEN_QUESTION,
    HUNDRED_QUESTION,
    SORT_QUESTION
}

fun createChoiceView(root: ViewGroup, context: Context, questionNum: Int): View {
    val view =
        LayoutInflater.from(context).inflate(R.layout.layout_question_item, root, false)
    view.iv_question_sub.visibility = View.VISIBLE
    view.tv_question_number.visibility = View.VISIBLE
    view.iv_question_add.visibility = View.INVISIBLE
    view.tv_question_number.text = 'A'.plus(root.childCount) + "."
    view.et_question_content.hint = "选项内容..."
    view.iv_question_sub.setOnClickListener {
        var position = root.indexOfChild(view)
        QuestionData.removeChoice(questionNum, position)

        root.removeView(view)
        while (position < root.childCount) {
            val child = root.getChildAt(position)
            child.tv_question_number.text = 'A'.plus(position) + "."
            position++
        }


    }
    view.iv_question_add.setOnClickListener {
        QuestionData.addChoice(questionNum)

        it.visibility = View.INVISIBLE
        view.tv_question_number.visibility = View.VISIBLE
        view.iv_question_sub.visibility = View.VISIBLE
        view.tv_question_number.text = 'A'.plus(root.childCount - 1) + "."
        root.addView(createChildView(root, context,questionNum))
        view.et_question_content.hint = "选项内容..."
    }
    return view
}

@Synchronized
fun createChildView(root: ViewGroup, context: Context, questionNum: Int): View {
    val view =
        LayoutInflater.from(context).inflate(R.layout.layout_question_item, root, false)
    view.tv_question_number.visibility = View.INVISIBLE
    view.iv_question_add.visibility = View.VISIBLE
    view.iv_question_sub.setOnClickListener {
        var position = root.indexOfChild(view)
        QuestionData.removeChoice(questionNum, position)

        root.removeView(view)
        while (position < root.childCount) {
            val child = root.getChildAt(position)
            child.tv_question_number.text = 'A'.plus(position) + "."
            position++
        }


    }
    view.iv_question_add.setOnClickListener {
        QuestionData.addChoice(questionNum)

        view.iv_question_sub.visibility = View.VISIBLE
        view.tv_question_number.text = 'A'.plus(root.childCount - 1) + "."
        root.addView(createChildView(root, context,questionNum))
        it.visibility = View.INVISIBLE
        view.tv_question_number.visibility = View.VISIBLE
        view.et_question_content.hint = "选项内容..."
    }
    return view
}