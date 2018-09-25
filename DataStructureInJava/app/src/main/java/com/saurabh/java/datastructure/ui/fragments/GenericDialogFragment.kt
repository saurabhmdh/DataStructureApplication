package com.saurabh.java.datastructure.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.saurabh.java.datastructure.R

class GenericDialogFragment : DialogFragment() {
    var title = "title"
    var msg = "msg"
    var okText = "OK"
    var cancelText = "cancel"
    var onOkClickListener : DialogInterface.OnClickListener? = null
    var onCancelClickListener : DialogInterface.OnClickListener? = DialogInterface.OnClickListener { _, _ -> }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.MaterialDialogTheme)
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(okText, onOkClickListener)
                .setNegativeButton(cancelText, onCancelClickListener)
        return builder.create()
    }
}