package com.example.campusform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusform.commons.Item
import com.example.campusform.commons.ItemAdapter
import com.example.campusform.commons.ItemManager
import com.example.campusform.service.bindNonNull
import com.example.campusform.service.listLiveData
import kotlinx.android.synthetic.main.fragment_open.view.*

class OpenFragment : Fragment() {
    private val itemManager = ItemManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_open, container, false)
        view.srl_frag_open.apply {
            setOnRefreshListener {
                setColorSchemeResources(R.color.colorAccent)
                //refresh
            }
        }

        view.rv_frag_open.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ItemAdapter(itemManager)
            //get list
        }

        //bind

        listLiveData.bindNonNull(this) { list ->
            val items = mutableListOf<Item>()
            list.forEach {
                items.listItem {
                    name.text = it
                }
            }


        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = OpenFragment()
    }

}