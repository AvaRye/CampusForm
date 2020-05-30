package com.example.campusform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_toolbar.view.*

class ListActivity : AppCompatActivity() {

    private lateinit var openFragment: OpenFragment
    private lateinit var closedFragment: ClosedFragment
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private val pagerAdapter = PagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        tb_list.apply {
            tv_tb_title.text = resources.getString(R.string.question)
            iv_tb_back.visibility = View.VISIBLE
            iv_tb_avatar.visibility = View.VISIBLE
            iv_tb_back.setOnClickListener {
                onBackPressed()
            }
            iv_tb_avatar.setOnClickListener {
                //popupwindow
            }
        }

        openFragment = OpenFragment.newInstance()
        closedFragment = ClosedFragment.newInstance()
        tabLayout = tl_list
        viewPager = vp_list
        pagerAdapter.apply {
            add(openFragment, "已开放")
            add(closedFragment, "未开放")
        }
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)


        /*elv_list.apply {
            setGroupIndicator(null)
            setAdapter(listAdapter)
            tv_list_child_name.setOnClickListener {
                //跳转到预览
                Toast.makeText(this@ListActivity, "预览", Toast.LENGTH_SHORT).show()
            }
            iv_list_child_delete.setOnClickListener {
                deleteItem()
                refresh()
            }

        }
*/
        fab_new.setOnClickListener {
            //跳转到新建
            Toast.makeText(this, "新建", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, CreateQuestionsActivity::class.java))
        }
    }

    private fun deleteItem() {

    }

    private fun refresh() {

    }
}
