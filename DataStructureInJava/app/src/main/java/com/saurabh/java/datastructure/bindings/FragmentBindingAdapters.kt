package com.saurabh.java.datastructure.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import javax.inject.Inject

/**
 * Binding adapters that work with a fragment instance.
 */

class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {
//    @BindingAdapter("imageUrl")
//    fun bindImage(imageView: ImageView, url: String?) {
//        Glide.with(fragment).load(url).into(imageView)
//    }

    @BindingAdapter("setBackgroundColor")
    fun View.setBackground(color: Int) {
        this.setBackgroundColor(color)
    }

    @BindingAdapter("imgRes")
    fun ImageView.setImage(drawable : Int) {
        this.setImageResource(drawable)
    }
}
