package com.saurabh.java.datastructure.ui.activities

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.util.LookupTable

import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import javax.inject.Inject
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView

import com.saurabh.java.datastructure.databinding.ActivityHomepageBinding
import com.saurabh.java.datastructure.interfaces.IActionBarTitleHandler
import com.saurabh.java.datastructure.ui.fragments.GenericDialogFragment
import com.saurabh.java.datastructure.ui.fragments.HomePageFragmentDirections
import com.saurabh.java.datastructure.vo.ActionbarItem


class Homepage : AppCompatActivity(),
        HasSupportFragmentInjector,
        IActionBarTitleHandler {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var lookupTable: LookupTable

    lateinit var mBinding: ActivityHomepageBinding
    lateinit var drawer: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_homepage)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(mBinding.toolbar)
        drawer = mBinding.drawerLayout

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val topLevelDestinations = setOf(R.id.id_home)

        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).setDrawerLayout(drawer).build()
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(mBinding.navView, navController)

        mBinding.navView.setNavigationItemSelectedListener(NavigationListener())
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector


    private fun setToolbarTitle(title: String) {
        mBinding.collapsingToolbar.title = title
        mBinding.appbar.setExpanded(true)
    }

    override fun updateActionBarTitle(item: ActionbarItem) {
        setToolbarTitle(item.title)
        if (item.categoryIcon != 0) {
            mBinding.ivCategoryLogo.setImageResource(item.categoryIcon)
        }
    }

    private fun launchFragment(section: Int) {
        lookupTable.getCategory(section)?.let {category ->
            navController.navigate(HomePageFragmentDirections.actionNavigateProgram(category, category.titleId))
        }
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

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    internal inner class NavigationListener : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_item_home -> navController.navigate(R.id.id_home)
                R.id.navigation_item_faqs -> navController.navigate(HomePageFragmentDirections.actionNavigateFaq())
                R.id.navigation_item_favourite -> navController.navigate(HomePageFragmentDirections.actionNavigateFavorite())
                R.id.navigation_item_linked_list -> launchFragment(0)
                R.id.navigation_item_stack -> launchFragment(1)
                R.id.navigation_item_queues -> launchFragment(2)
                R.id.navigation_item_trees -> launchFragment(3)
                R.id.navigation_item_graphs -> launchFragment(4)
                R.id.navigation_item_searching -> launchFragment(5)
                R.id.navigation_item_sorting -> launchFragment(6)
                R.id.navigation_item_support -> showFeedbackDialog()
                R.id.navigation_item_rate_us -> showRateThisAppDialog()
            }
            drawer.closeDrawer(GravityCompat.START)
            return false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
