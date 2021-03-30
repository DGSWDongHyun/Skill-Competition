package com.simple.module2.ui.activites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.module2.DrawerItems
import com.simple.module2.R
import com.simple.module2.ResAmountModel
import com.simple.module2.net.RetrofitModule
import com.simple.module2.net.retrofit.Retrofit
import com.simple.module2.ui.adapter.NavigationDrawerAdapter
import com.simple.module2.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(applicationContext, SplashActivity::class.java))
        updateWidget()
    }

    fun updateWidget() {

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val service = RetrofitModule.getInstance()
        val api = service.create(Retrofit::class.java)
        val amountText = findViewById<TextView>(R.id.amountText)

        api.getAmount().enqueue(object : Callback<ResAmountModel>{
            override fun onResponse(
                call: Call<ResAmountModel>,
                response: Response<ResAmountModel>
            ) {
                if(response.code() == 200) {
                    viewModel.availablePrice.value = response.body()!!.amount.toString()
                }
            }

            override fun onFailure(call: Call<ResAmountModel>, t: Throwable) {
                Log.d("TAG", "${t.message}")
            }
        })

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        viewModel.availablePrice.observe(this, {
            amountText.text = "${it}원"
        })

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_search, R.id.nav_charge, R.id.nav_info
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        bottomNav.setupWithNavController(navController)

        recyclerViewDrawer.adapter = NavigationDrawerAdapter(setUpDummy())
        recyclerViewDrawer.layoutManager = LinearLayoutManager(this)
    }

    fun setUpDummy() : ArrayList<DrawerItems> {
        val list = ArrayList<DrawerItems>()

        list.add(
            DrawerItems("결제", "카드등록                          계좌등록\n" +
                    "머니충전                          온라인결제")
        )

        list.add(
            DrawerItems("포인트", "적립/사용내역               추후적립\n" +
                    "비밀번호                         이벤트")
        )

        list.add(
            DrawerItems("금융", "신용카드                          보험\n" +
                    "대출                                  부동산")
        )

        list.add(
            DrawerItems("서비스 안내", "적립/사용내역               추후적립\n" +
                    "비밀번호                         이벤트")
        )

        list.add(
                DrawerItems("관련사이트", "HRD Korea                  GIFTS")
        )


        return list
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}