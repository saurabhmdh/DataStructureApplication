package com.saurabh.java.datastructure.ui.activities

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.interfaces.IActionBarTitleHandler
import com.saurabh.java.datastructure.interfaces.IFragmentLifeCycleEvent
import com.saurabh.java.datastructure.util.LookupTable
import com.saurabh.java.datastructure.util.instanceOf
import com.saurabh.java.datastructure.vo.ActionbarItem
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_homepage.*
import javax.inject.Inject
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.saurabh.java.datastructure.ui.fragments.*
import timber.log.Timber


class Homepage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector,
        IFragmentLifeCycleEvent, IActionBarTitleHandler {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var lookupTable: LookupTable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        setupToolbar()
        pushFragment(instanceOf<HomePageFragment>())
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        appBarLayout.setExpanded(true)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (!popFragment()) {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.homepage, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_item_home -> {
                handleHomePageFragment()
            }
            R.id.navigation_item_faqs -> {
                pushFragment(instanceOf<FaqsFragment>())
            }
            R.id.navigation_item_favourite -> {
                //TODO: write code for fav fragment
            }
            R.id.navigation_item_linked_list -> {
                launchFragment(0)
            }
            R.id.navigation_item_stack -> {
                launchFragment(1)
            }
            R.id.navigation_item_queues -> {
                launchFragment(2)
            }
            R.id.navigation_item_trees -> {
                launchFragment(3)
            }
            R.id.navigation_item_graphs -> {
                launchFragment(4)
            }
            R.id.navigation_item_searching -> {
                launchFragment(5)
            }
            R.id.navigation_item_sorting -> {
                launchFragment(6)
            }
            R.id.navigation_item_support -> {
                showFeedbackDialog()
            }
            R.id.navigation_item_rate_us -> {
                showRateThisAppDialog()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleHomePageFragment() {
        var count: Int = supportFragmentManager.backStackEntryCount
        while (count > 1) {
            popFragment()
            count--
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment, fragment.toString()).addToBackStack(fragment.toString()).commitAllowingStateLoss()
    }

    override fun popFragment(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 1) { //Don't remove homepage fragment
            val fragment = supportFragmentManager.fragments[supportFragmentManager.backStackEntryCount - 2]
            fragment as BaseFragment
            val item: ActionbarItem = fragment.getTitle()
            supportFragmentManager.popBackStack()
            updateActionBarTitle(item)
            val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
            appBarLayout.setExpanded(isAppBarExpanded(appBarLayout))
            return true
        }
        return false
    }

    private fun setToolbarTitle(title: String) {
        val collapseToolbar =  findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapseToolbar.title = title
        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        appBarLayout.setExpanded(true)
    }

    override fun updateActionBarTitle(item: ActionbarItem) {
        setToolbarTitle(item.title)
        if (item.categoryIcon != 0) {
            findViewById<AppCompatImageView>(R.id.iv_category_logo).setImageResource(item.categoryIcon)
        }
    }

    private fun launchFragment(section: Int) {
        lookupTable.getCategory(section)?.let {category ->
            val fragment = instanceOf<ProgramsFragment>(Pair(Constants.BUNDLE_KEY, category.titleId),
                    Pair(Constants.BUNDLE_OBJECT, category))
            pushFragment(fragment)
        }
    }

    private fun isAppBarExpanded(abl: AppBarLayout): Boolean {
        val behavior = (abl.layoutParams as CoordinatorLayout.LayoutParams).behavior
        return if (behavior is AppBarLayout.Behavior) behavior.topAndBottomOffset == 0 else false
    }

    private fun showFeedbackDialog() {
        val dialog = GenericDialogFragment()
        dialog.title = getString(R.string.dialog_title_feedback)
        dialog.msg = getString(R.string.dialog_message_feedback)
        dialog.okText = getString(R.string.feedback)
        dialog.onOkClickListener = DialogInterface.OnClickListener { popup, _ ->
            startFeedBackEmailIntent()
            popup.dismiss()
        }
        dialog.cancelText = getString(R.string.later)
        dialog.onCancelClickListener = DialogInterface.OnClickListener { popup, _ ->
            popup.dismiss()
        }
        dialog.show(supportFragmentManager, "showFeedbackDialog")
    }

    private fun startFeedBackEmailIntent() {
        val intent = Intent(Intent.ACTION_VIEW)
        val data = Uri.parse("mailto:saurabhmdh@gmail.com?subject=" + getString(R.string.support_and_feedback))
        intent.data = data
        startActivity(intent)
    }

    private fun showRateThisAppDialog() {
        val dialog = GenericDialogFragment()
        dialog.title = getString(R.string.dialog_title_rate_this_app)
        dialog.msg = getString(R.string.dialog_message_rate_this_app)
        dialog.okText = getString(R.string.google_play)
        dialog.onOkClickListener = DialogInterface.OnClickListener { popup, _ ->
            val appPackageName = packageName
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            popup.dismiss()
        }
        dialog.cancelText = getString(R.string.later)
        dialog.onCancelClickListener = DialogInterface.OnClickListener { popup, _ ->
            popup.dismiss()
        }
        dialog.show(supportFragmentManager, "showRateThisAppDialog")
    }

}
