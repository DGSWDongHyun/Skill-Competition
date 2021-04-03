package com.simple.module2_3.ui.home

import android.content.Intent
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
import com.simple.module2_3.PaymentActivity
import com.simple.module2_3.R
import com.simple.module2_3.adapter.AroundStoreAdapter
import com.simple.module2_3.adapter.NoticeListAdapter
import com.simple.module2_3.adapter.SwipeAdapter
import com.simple.module2_3.adapter.listener.OnClickListenerAround
import com.simple.module2_3.data.ResNoticeList
import com.simple.module2_3.data.ResStoreList
import com.simple.module2_3.data.ResSwipeList
import com.simple.module2_3.data.StoreList
import com.simple.module2_3.data.module.ExceptionModule.shutOffApp
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
        val noticeRecycler = view.findViewById<RecyclerView>(R.id.noticeRecycler)
        val aroundRecycler = view.findViewById<RecyclerView>(R.id.aroundRecycler)

        api.getNoticeList().enqueue(object : Callback<ResNoticeList> {
            override fun onResponse(call: Call<ResNoticeList>, response: Response<ResNoticeList>) {
                if(response.code() == 200){
                    noticeRecycler.layoutManager = LinearLayoutManager(requireContext())
                    noticeRecycler.adapter = NoticeListAdapter(response.body()!!.list)
                }else {
                    shutOffApp(requireActivity())
                }
            }

            override fun onFailure(call: Call<ResNoticeList>, t: Throwable) {
                shutOffApp(requireActivity())
            }

        })

        api.getStoreList().enqueue(object : Callback<ResStoreList> {
            override fun onResponse(call: Call<ResStoreList>, response: Response<ResStoreList>) {
                if(response.code() == 200) {
                    aroundRecycler.adapter = AroundStoreAdapter(response.body()!!.list, object : OnClickListenerAround {
                        override fun onClickItem(data: StoreList) {
                            val intent = Intent(requireContext(), PaymentActivity::class.java)
                            intent.putExtra("storeUid", data.storeUid)
                            startActivity(intent)
                        }
                    })
                    aroundRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }else {
                    shutOffApp(requireActivity())
                }
            }

            override fun onFailure(call: Call<ResStoreList>, t: Throwable) {
                shutOffApp(requireActivity())
            }
        })

        api.getSwipeList().enqueue(object : Callback<ResSwipeList> {
            override fun onResponse(call: Call<ResSwipeList>, response: Response<ResSwipeList>) {
               if(response.code() == 200) {
                   swipeRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                   swipeRecycler.adapter = SwipeAdapter(response.body()!!.list)

                   val snapHelper = PagerSnapHelper()
                   snapHelper.attachToRecyclerView(swipeRecycler)
               }else {
                   shutOffApp(requireActivity())
               }
            }

            override fun onFailure(call: Call<ResSwipeList>, t: Throwable) {
               Log.d("TAG", "${t.message}")
                shutOffApp(requireActivity())
            }

        })
    }

}