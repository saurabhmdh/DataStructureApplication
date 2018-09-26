package com.saurabh.java.datastructure.util

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import com.saurabh.java.datastructure.R

object Utils {
    fun copyToClipboard(context: Context, programCode: String) {
        val clipboard = context.getSystemService(Activity.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.primaryClip = ClipData.newPlainText(context.resources.getString(R.string.app_name), programCode)
    }

    fun shareCodesViaApps(context: Context, programCode: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, context.resources.getString(R.string.app_name))
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, programCode)
        context.startActivity(Intent.createChooser(sharingIntent, context.resources.getString(R.string.share_using)))
    }
}