package com.saurabh.java.datastructure.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.db.tables.FAQ
import com.saurabh.java.datastructure.ui.fragments.QuestionCardFragment
import com.saurabh.java.datastructure.util.instanceOf

class QuestionCardViewPagerAdapter(val items: List<FAQ>, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return instanceOf<QuestionCardFragment>(
                Pair(Constants.BUNDLE_POSITION, position),
                Pair(Constants.BUNDLE_OBJECT, items[position]))
    }

    override fun getCount(): Int {
        return items.size
    }
}