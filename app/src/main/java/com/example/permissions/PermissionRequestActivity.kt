package com.example.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.myapplication.R
import java.util.*

/**
 * Created by zouxipu on 2021/4/14.
 * zouxipu@haohaozhu.com
 * Traveling Light
 */

class PermissionRequestActivity : Activity() {
    private var requestCode = 0
    private var permissions = Array(0) { "" }

    companion object {
        private var permissionListener: PermissionInterface? = null
        private const val PERMISSION_NAME = "permission_name"
        private const val REQUEST_CODE = "request_code"

        /**s
         * 开始申请
         */
        @JvmStatic
        fun requestPermission(ctx: Context, permissions: Array<out String>, requestCode: Int, permissionListener: PermissionInterface) {
            Companion.permissionListener = permissionListener
            val intent = Intent(ctx, PermissionRequestActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val bundle = Bundle()
            bundle.putStringArray(PERMISSION_NAME, permissions)
            bundle.putInt(REQUEST_CODE, requestCode)
            intent.putExtras(bundle)
            ctx.startActivity(intent)
            //去除动画
            if (ctx is Activity) {
                (ctx as Activity).overridePendingTransition(0, 0)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.TextAppearance_AppCompat)
        setContentView(R.layout.activity_permission)
        val bundle = intent.extras
        if (bundle != null) {
            permissions = bundle.getStringArray(PERMISSION_NAME)!!
            requestCode = bundle.getInt(REQUEST_CODE, 0)
        }
        if (permissions.isNullOrEmpty()) {
            finish()
        }
        requestPermission(permissions)
    }

    private fun requestPermission(permissions: Array<String>) {
        if (PermissionUtil.checkHasPermissions(this, *permissions)) {
            //all permissions granted
            if (permissionListener != null) {
                permissionListener!!.PermissionGranted()
                permissionListener = null
            }
            finish()
            overridePendingTransition(0, 0)
        } else {
            //request permissions
            ActivityCompat.requestPermissions(this, permissions, requestCode)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (PermissionUtil.checkVerifyPermissions(*grantResults)) {
            //所有权限通过
            if (permissionListener != null) {
                permissionListener!!.PermissionGranted()
            }
        } else {
            //权限被拒绝
            if (!PermissionUtil.shouldShowRequestPermissionRationale(this, *permissions)) {
                if (permissions.size !== grantResults.size) {
                    return
                }
                val rejectList: ArrayList<String> = ArrayList()
                for (i in grantResults.indices) {
                    if (grantResults[i] == -1) {
                        rejectList.add(permissions[i])
                    }
                }
                if (permissionListener != null) {
                    permissionListener!!.PermissionReject(requestCode, rejectList)
                }
            }
            //权限被取消
            else {
                if (permissionListener != null) {
                    permissionListener!!.PermissionCanceled(requestCode)
                }
            }
        }
        permissionListener = null
        finish()
        overridePendingTransition(0, 0)
    }


}