package com.example.campusform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusform.commons.ItemAdapter
import com.example.campusform.commons.ItemManager
import kotlinx.android.synthetic.main.fragment_closed.view.*

class ClosedFragment : Fragment() {
    private val itemManager = ItemManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_closed, container, false)
        view.srl_frag_closed.apply {
            setOnRefreshListener {
                setColorSchemeResources(R.color.colorAccent)
                //refresh
            }
        }

        view.rv_frag_closed.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ItemAdapter(itemManager)
            //get list
        }

        //bind

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClosedFragment()
    }

}