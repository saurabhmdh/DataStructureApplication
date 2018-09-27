package com.saurabh.java.datastructure.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.pddstudio.highlightjs.HighlightJsView
import com.pddstudio.highlightjs.models.Language
import com.pddstudio.highlightjs.models.Theme
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

    @BindingAdapter("setSourceCode")
    fun HighlightJsView.setSourceCode(sourceCode: String) {
        this.theme = Theme.ANDROID_STUDIO
        this.highlightLanguage = Language.JAVA
        this.setSource(sourceCode)
    }
}
