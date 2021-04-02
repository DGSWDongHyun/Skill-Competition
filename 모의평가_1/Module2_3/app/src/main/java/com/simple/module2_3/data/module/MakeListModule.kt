package com.simple.module2_3.data.module

import com.simple.module2_3.R
import com.simple.module2_3.data.Horizontal
import com.simple.module2_3.data.Vertical

object MakeListModule {

    var searchedList : ArrayList<String> = arrayListOf()

    fun makeHorizontal() : ArrayList<Horizontal> {
        val list : ArrayList<Horizontal> = arrayListOf()

        list.add(Horizontal("이용내역", R.drawable.ic_chrome_reader_mode_24px))
        list.add(Horizontal("결제수단", R.drawable.ic_credit_card_24px))
        list.add(Horizontal("선물함", R.drawable.ic_card_giftcard_24px))
        list.add(Horizontal("쿠폰함", R.drawable.ic_card_membership_24px))

        return list
    }

    fun makeVertical() : ArrayList<Vertical> {
        val list : ArrayList<Vertical> = arrayListOf()

        list.add(
            Vertical("결제", "카드등록                          계좌등록\n" +
                "머니충전                          온라인결제")
        )

        list.add(
            Vertical("포인트", "적립/사용내역               추후적립\n" +
                    "비밀번호                         이벤트")
        )

        list.add(
            Vertical("금융", "신용카드                          보험\n" +
                    "대출                                  부동산")
        )

        list.add(
            Vertical("서비스 안내", "적립/사용내역               추후적립\n" +
                "비밀번호                         이벤트")
        )

        list.add(
            Vertical("관련 사이트", "HRD Korea                  GIFTS")
        )

        return list
    }

    fun setDataResume(dataList : ArrayList<String>) {
        this.searchedList = dataList
    }
}