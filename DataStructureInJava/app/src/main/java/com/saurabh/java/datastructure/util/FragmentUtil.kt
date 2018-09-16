package com.saurabh.java.datastructure.util

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

/**
 * Pass arguments to a Fragment without the hassle of
 * creating a static newInstance() method for every Fragment.
 *
 * Declared outside any class to have full access in any
 * part of your package.
 *
 * Usage: instanceOf<MyFragment>("foo" to true, "bar" to 0)
 *
 * @return Returns an instance of Fragment as the specified generic type with the params applied as arguments
 */
inline fun <reified T : Fragment> instanceOf(vararg params: Pair<String, Any>)
        = T::class.java.newInstance().apply {
    arguments = bundleOf(*params)
}