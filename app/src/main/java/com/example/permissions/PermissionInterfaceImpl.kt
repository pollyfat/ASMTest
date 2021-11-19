package com.example.permissions

/**
 *
 * @author: hmei
 * @date: 2021/11/18
 * @email: huangmei@haohaozhu.com
 *
 */
class PermissionInterfaceImpl:PermissionInterface {
    override fun PermissionGranted() {
    }

    override fun PermissionCanceled(requestCode: Int) {
    }

    override fun PermissionReject(requestCode: Int, strings: MutableList<String>?) {
    }

    fun runOnGranted(){}
}