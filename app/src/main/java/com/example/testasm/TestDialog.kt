package com.example.testasm

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 *
 * @author: hmei
 * @date: 2021/11/12
 * @email: huangmei@haohaozhu.com
 *
 */
class TestDialog :BottomSheetDialogFragment(){
    companion object {
        fun getInstance(): TestDialog{
            return TestDialog()
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(View.inflate(context, R.layout.dialog_test, null))
        dialog.setOnKeyListener(object: DialogInterface.OnKeyListener{
            override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        })
        return dialog
    }
}