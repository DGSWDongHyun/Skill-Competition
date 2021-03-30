package com.simple.module2.ui.fragments.search

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2.R
import com.simple.module2.SearchedModel
import com.simple.module2.data.SearchedListObject
import com.simple.module2.ui.adapter.SearchedAdapter
import com.simple.module2.ui.adapter.listener.onItemDeleteListener
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    var dataList : ArrayList<SearchedModel> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.searchedRecyclerView)
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        checkRecentView(view)

        val adapter = SearchedAdapter(dataList, object : onItemDeleteListener {
            override fun onDelete(data: SearchedModel) {
                dataList.remove(data)
                checkRecentView(view)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                dataList.add(SearchedModel(query))
                adapter.setData(dataList)
                checkRecentView(view)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })



    }
    private fun checkRecentView(view : View) {
        val recentView = view.findViewById<ConstraintLayout>(R.id.recentSearchView)

        if(dataList.size == 0) {
            recentView.visibility = View.VISIBLE
        }else{
            recentView.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        dataList = SearchedListObject.searchedList
    }

    override fun onDestroy() {
        super.onDestroy()
        SearchedListObject.searchedList = dataList
    }
}