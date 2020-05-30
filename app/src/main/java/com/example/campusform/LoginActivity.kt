package com.example.campusform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.item_toolbar.view.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tb_login.apply {
            iv_tb_back.visibility = View.GONE
            iv_tb_avatar.visibility = View.GONE
        }

        tv_login.setOnClickListener {
            if (login(et_username.text.toString(), et_password.text.toString())) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

    }

    private fun login(username: String, password: String): Boolean {
        return true
    }
}
