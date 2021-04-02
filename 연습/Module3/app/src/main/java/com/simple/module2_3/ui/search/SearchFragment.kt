package com.simple.module2_3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2_3.R
import com.simple.module2_3.adapter.SearchedAdapter
import com.simple.module2_3.adapter.listener.OnClickDeleteListener
import com.simple.module2_3.data.module.MakeListModule

class SearchFragment : Fragment() {

    var dataList : ArrayList<String> = arrayListOf()
    private lateinit var adapter : SearchedAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_search, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<SearchView>(R.id.searchView)

        adapter = SearchedAdapter(dataList, object : OnClickDeleteListener {
            override fun onClickItem(data: String) {
                dataList.remove(data)
                checkListSize(view)
            }
        })

        adapter.setData(dataList)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                dataList.add(query!!)
                searchView.setQuery("", false)
                adapter.setData(dataList)
                checkListSize(view)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })

        val recyclerSearched = view.findViewById<RecyclerView>(R.id.recyclerSearched)
        recyclerSearched.layoutManager = LinearLayoutManager(requireContext())
        recyclerSearched.adapter = adapter
    }

    fun checkListSize(view : View) {
        val clearSearched = view.findViewById<ConstraintLayout>(R.id.clearSearched)

        if(dataList.size == 0)
            clearSearched.visibility = View.VISIBLE
        else
            clearSearched.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        MakeListModule.setDataResume(dataList)
    }

    override fun onResume() {
        super.onResume()
        dataList = MakeListModule.searchedList
        adapter.setData(dataList)
        checkListSize(requireView())
    }
}