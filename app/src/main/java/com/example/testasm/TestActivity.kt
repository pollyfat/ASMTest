package com.example.testasm


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.example.myapplication.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 *
 * @author: hmei
 * @date: 2021/11/3
 * @email: huangmei@haohaozhu.com
 *
 */
class TestActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.current_this).setOnClickListener(this)
        findViewById<Button>(R.id.lambda_btn).setOnClickListener { }
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, TestJavaActivity::class.java))
    }
}