package com.simple.module2_3.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.simple.module2_3.R
import com.simple.module2_3.adapter.SwipeAdapter
import com.simple.module2_3.data.ResSwipeList
import com.simple.module2_3.net.network.Network
import com.simple.module2_3.net.retrofit.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = RetrofitModule.getInstance()
        val api = service.create(Network::class.java)

        val swipeRecycler = view.findViewById<RecyclerView>(R.id.swipeRecycler)

        api.getSwipeList().enqueue(object : Callback<ResSwipeList> {
            override fun onResponse(call: Call<ResSwipeList>, response: Response<ResSwipeList>) {
               if(response.code() == 200) {
                   swipeRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                   swipeRecycler.adapter = SwipeAdapter(response.body()!!.list)

                   val snapHelper = PagerSnapHelper()
                   snapHelper.attachToRecyclerView(swipeRecycler)
               }else {
                   Toast.makeText(requireContext(), "서버와의 통신 실패로 어플리케이션을 종료합니다.", Toast.LENGTH_LONG).show()

                   requireActivity().moveTaskToBack(true)
                   requireActivity().finishAndRemoveTask()
               }
            }

            override fun onFailure(call: Call<ResSwipeList>, t: Throwable) {
               Log.d("TAG", "${t.message}")
            }

        })
    }
}