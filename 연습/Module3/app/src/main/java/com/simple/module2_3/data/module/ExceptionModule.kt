package com.simple.module2_3.data.module

import android.app.Activity
import android.widget.Toast

object ExceptionModule {
     fun shutOffApp(activity: Activity) {
        Toast.makeText(activity, "서버와의 통신 실패로 어플리케이션을 종료합니다.", Toast.LENGTH_LONG).show()

         activity.moveTaskToBack(true)
         activity.finishAndRemoveTask()
    }
}