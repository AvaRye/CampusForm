package com.example.campusform

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_home.view.*

class HomeActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        layout_item_question.tv_item_home.text = "问卷"
        layout_item_vote.tv_item_home.text = "投票"
        layout_item_theory.tv_item_home.text = "理论答题"
        iv_home_back.setOnClickListener {
            onBackPressed()
        }
        iv_home_avatar.setOnClickListener {
            //popupwindow
        }
        layout_item_theory.setOnClickListener {
            //跳转理论答题
            Toast.makeText(this,"理论答题",Toast.LENGTH_SHORT).show()

        }
        layout_item_vote.setOnClickListener {
            //跳转投票
            Toast.makeText(this,"投票",Toast.LENGTH_SHORT).show()

        }
        layout_item_question.setOnClickListener {
            //跳转问卷
            Toast.makeText(this,"问卷",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,CreateActivity::class.java))
        }
    }
}