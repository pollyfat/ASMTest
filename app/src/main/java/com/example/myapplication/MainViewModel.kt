//package com.example.myapplication
//
//import android.view.View
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
///**
// *
// * @author: hmei
// * @date: 2021/11/1
// * @email: huangmei@haohaozhu.com
// *
// */
//class MainViewModel : ViewModel() {
//
//    val clickCount = MutableLiveData<Int>().apply { value = 0 }
//    fun clickFab(v: View) {
//        clickCount.value = clickCount.value!! + 1
//    }
//}