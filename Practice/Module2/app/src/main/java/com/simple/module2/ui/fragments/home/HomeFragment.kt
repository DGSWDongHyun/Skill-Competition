package com.simple.module2.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.simple.module2.*
import com.simple.module2.net.RetrofitModule
import com.simple.module2.net.retrofit.Retrofit
import com.simple.module2.ui.activites.NoticeActivity
import com.simple.module2.ui.activites.PaymentActivity
import com.simple.module2.ui.adapter.AroundRestaurantAdapter
import com.simple.module2.ui.adapter.BannerAdapter
import com.simple.module2.ui.adapter.NoticeAdapter
import com.simple.module2.ui.adapter.listener.onClickItemListener
import com.simple.module2.ui.adapter.listener.onClickItemListenerAdapter
import com.simple.module2.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var arrayList : ArrayList<NoticeModel> = arrayListOf()
    private var arrayAround : ArrayList<AroundData> = arrayListOf()
    private var arrayBanner : ArrayList<SwipeBannerData> = arrayListOf()

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

        updateWidget(view)
    }

    private fun updateWidget(view : View) {
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val service = RetrofitModule.getInstance()
        val api = service.create(Retrofit::class.java)
        val recyclerNotice = view.findViewById<RecyclerView>(R.id.recyclerNotice)
        val recyclerStore = view.findViewById<RecyclerView>(R.id.recyclerAround)
        val recyclerBanner = view.findViewById<RecyclerView>(R.id.recyclerBanner)

        api.getStoreList().enqueue(object : Callback<ResAroundRestaurant> {
            override fun onResponse(
                call: Call<ResAroundRestaurant>,
                response: Response<ResAroundRestaurant>
            ) {
                if(response.code() == 200) {
                    arrayAround = response.body()!!.list

                    recyclerStore.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    recyclerStore.adapter = AroundRestaurantAdapter(requireContext() ,arrayAround, object : onClickItemListener {
                        override fun onClickItem(data: AroundData) {
                            val intent = Intent(requireActivity(), PaymentActivity::class.java)
                            intent.putExtra("storeUid", data.storeUid)
                            intent.putExtra("availablePrice", viewModel.availablePrice.value)
                            intent.putExtra("storeName", data.storeName)

                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<ResAroundRestaurant>, t: Throwable) {
                Log.d("TAG", "${t.message}")
            }

        })

        api.getSwipeBanner().enqueue(object : Callback<ResSwipeModel> {
            override fun onResponse(call: Call<ResSwipeModel>, response: Response<ResSwipeModel>) {
                if(response.code() == 200) {
                    arrayBanner = response.body()!!.list

                    recyclerBanner.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    recyclerBanner.adapter = BannerAdapter(requireContext() , arrayBanner)

                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(recyclerBanner)

                }
            }

            override fun onFailure(call: Call<ResSwipeModel>, t: Throwable) {
                Log.d("TAG", "${t.message}")
            }

        })

        api.getNotice().enqueue(object : Callback<ResNoticeModel> {
            override fun onResponse(
                call: Call<ResNoticeModel>,
                response: Response<ResNoticeModel>
            ) {
                if(response.code() == 200) {
                    arrayList = response.body()!!.list

                    recyclerNotice.layoutManager = LinearLayoutManager(requireContext())
                    recyclerNotice.adapter = NoticeAdapter(arrayList, object : onClickItemListenerAdapter {
                        override fun onClickItem(data: NoticeModel) {
                            val intent = Intent(requireActivity(), NoticeActivity::class.java)
                            intent.putExtra("title",data.title)
                            intent.putExtra("cn", data.cn)
                            intent.putExtra("date", SimpleDateFormat("yyyy.MM.dd").format(Date(data.registDt!!.toLong())))
                            intent.putExtra("availablePrice", viewModel.availablePrice.value)

                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<ResNoticeModel>, t: Throwable) {
                Log.d("TAG", "${t.message}")
            }

        })
    }
}