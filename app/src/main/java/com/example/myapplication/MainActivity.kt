package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 *
 * @author: hmei
 * @date: 2021/10/29
 * @email: huangmei@haohaozhu.com
 *
 */
class MainActivity(contentLayoutId: Int = R.layout.activity_main) : AppCompatActivity(contentLayoutId), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { this }
    }

    override fun onClick(p0: View?) {
        Toast.makeText(this, "KT_PLUGIN", Toast.LENGTH_SHORT).show()
    }


}