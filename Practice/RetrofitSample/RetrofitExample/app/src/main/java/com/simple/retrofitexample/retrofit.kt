package com.simple.retrofitexample
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.163.40:4000/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()