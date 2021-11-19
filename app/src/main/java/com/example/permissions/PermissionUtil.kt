package com.example.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.collection.SimpleArrayMap
import androidx.core.app.ActivityCompat

/**
 * Created by zouxipu on 2021/4/15.
 * zouxipu@haohaozhu.com
 * Traveling Light
 */

object PermissionUtil {
    private var MIN_SDK_PERMISSIONS: SimpleArrayMap<String, Int> = SimpleArrayMap(8)

    init {
        MIN_SDK_PERMISSIONS.put("com.android.voicemail.permission.ADD_VOICEMAIL", 14)
        MIN_SDK_PERMISSIONS.put("android.permission.BODY_SENSORS", 20)
        MIN_SDK_PERMISSIONS.put("android.permission.READ_CALL_LOG", 16)
        MIN_SDK_PERMISSIONS.put("android.permission.READ_EXTERNAL_STORAGE", 16)
        MIN_SDK_PERMISSIONS.put("android.permission.USE_SIP", 9)
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_CALL_LOG", 16)
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_SETTINGS", 23)
    }

    /**
     * 所有权限是否同意
     */
    fun checkHasPermissions(context: Context, vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (checkPermissionExists(permission) && !checkHasPermission(context, permission)) {
                return false
            }
        }
        return true
    }

    /**
     * 权限是否通过
     */
    private fun checkHasPermission(context: Context, permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(context, permission) === PackageManager.PERMISSION_GRANTED
    }

    /**
     * 权限是否存在
     */
    private fun checkPermissionExists(permission: String): Boolean {
        val minVersion: Int? = MIN_SDK_PERMISSIONS?.get(permission)
        return minVersion == null || Build.VERSION.SDK_INT >= minVersion
    }

    /**
     * 校验是否赋于权限
     */
    fun checkVerifyPermissions(vararg grantResults: Int): Boolean {
        if (grantResults.isEmpty()) return false
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 检查权限是否需要提示
     */
    fun shouldShowRequestPermissionRationale(activity: Activity?, vararg permissions: String?): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!, permission!!)) {
                return true
            }
        }
        return false
    }
}




