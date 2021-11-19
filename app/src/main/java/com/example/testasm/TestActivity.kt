package com.example.testasm


import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.permissions.RequestPermission
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *
 * @author: hmei
 * @date: 2021/11/3
 * @email: huangmei@haohaozhu.com
 *
 */
class TestActivity : AppCompatActivity(), View.OnClickListener {


    public val test: String = "abc"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<Button>(R.id.lambda_btn).setOnClickListener(this)
        val bc = object : BeforeClick {
            override fun doBeforeClick(someParam: String) {
//                runBlocking {
//                    launch {
//                        delay(1000L)
                findViewById<Button>(R.id.interface_click).setOnClickListener {
                    Toast.makeText(it.context, someParam, Toast.LENGTH_SHORT).show()
                }
//                    }
//                }
            }
        }
        bc.doBeforeClick("SOME PARAM")
        findViewById<Button>(R.id.show_dialog_btn).setOnClickListener {
            showDialog()
        }
    }

    fun test(){
        if (test == "test") {
            Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, TestJavaActivity::class.java))
    }

    interface BeforeClick {
        fun doBeforeClick(someParam: String)
    }

    @RequestPermission(permissions = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun showDialog() {
        val customDialog =
            AlertDialog.Builder(this).setTitle(title)
                .setNegativeButton("Negative Btn",
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                .setPositiveButton("Positive Btn",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "Button $which is clicked.", Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                    })
                .create()
        customDialog.setCancelable(true)
        customDialog.setCanceledOnTouchOutside(false)
        customDialog.show()
    }
}