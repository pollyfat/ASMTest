package com.example.permissions

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.anno_permission.Binding
import java.util.*

/**
 *
 * @author: hmei
 * @date: 2021/11/17
 * @email: huangmei@haohaozhu.com
 *
 */
class TestAnnoActivity(contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequestPermission(permissions = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun needPermission(var1: Object, var2: Int) {
        PermissionRequestActivity.requestPermission(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            1090,
            object : PermissionInterface {
                override fun PermissionGranted() {

                }

                override fun PermissionCanceled(requestCode: Int) {
                    permissionCancel()
                }

                override fun PermissionReject(requestCode: Int, strings: MutableList<String>?) {
                    permissionDeny()
                }
            })

    }

    @PermissionReject
    fun permissionDeny() {
        Toast.makeText(this, "Permission Deny", Toast.LENGTH_SHORT).show()
    }

    @PermissionCanceled
    fun permissionCancel() {
        Toast.makeText(this, "Permission Canceled", Toast.LENGTH_SHORT).show()
    }
}