package com.simple.module2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    var availablePrice : MutableLiveData<String> = MutableLiveData()
}