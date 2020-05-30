package com.example.campusform

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_toolbar.view.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        layout_item_question.tv_item_home.text = resources.getString(R.string.question)
        layout_item_vote.tv_item_home.text = resources.getString(R.string.vote)
        layout_item_theory.tv_item_home.text = resources.getString(R.string.answer)
        tb_home.apply {
            tv_tb_title.text = resources.getString(R.string.title)
            iv_tb_back.visibility = View.INVISIBLE
            iv_tb_avatar.visibility = View.VISIBLE
            iv_tb_back.setOnClickListener {
                onBackPressed()
            }
            iv_tb_avatar.setOnClickListener {
                //popupwindow
            }
        }

        layout_item_theory.setOnClickListener {
            //跳转理论答题
            Toast.makeText(this, "理论答题", Toast.LENGTH_SHORT).show()

        }
        layout_item_vote.setOnClickListener {
            //跳转投票
            Toast.makeText(this, "投票", Toast.LENGTH_SHORT).show()

        }
        layout_item_question.setOnClickListener {
            //跳转问卷
            Toast.makeText(this, "问卷", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ListActivity::class.java))
        }
    }
}