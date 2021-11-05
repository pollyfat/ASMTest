//package com.example.myapplication
//
//import android.os.Bundle
//import android.view.View
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.lifecycleScope
//import androidx.lifecycle.viewModelScope
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
///**
// *
// * @author: hmei
// * @date: 2021/10/29
// * @email: huangmei@haohaozhu.com
// *
// */
//class MainActivity(contentLayoutId: Int = R.layout.activity_main) :
//    AppCompatActivity(contentLayoutId) {
//    private val vm: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
//        }
//        vm.clickCount.observe(this){
//            findViewById<TextView>(R.id.tapCount).text = "$it click"
//        }
//    }
//
//
//}