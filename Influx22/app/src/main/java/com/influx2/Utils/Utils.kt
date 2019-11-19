package com.influx2.Utils

import android.app.Dialog
import android.content.Context
import android.view.Window

import com.influx2.R

object Utils {
    fun showProgressDialog(ctx: Context): Dialog {
        val dialog = Dialog(ctx, android.R.style.Theme_Translucent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(false)
        return dialog
    }
}
