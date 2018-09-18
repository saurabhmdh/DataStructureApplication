package com.saurabh.java.datastructure.ui.activities

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.interfaces.IFragmentLifeCycleEvent
import com.saurabh.java.datastructure.ui.fragments.HomePageFragment
import com.saurabh.java.datastructure.util.instanceOf
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.app_bar_homepage.*
import javax.inject.Inject

class Homepage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector,
        IFragmentLifeCycleEvent {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        savedInstanceState.let {
            val fragment = instanceOf<HomePageFragment>()
            supportFragmentManager.beginTransaction().add(R.id.container_fragment, fragment, fragment.toString()).commit()
        }


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 0) {
               popFragment()
            } else {
                super.onBackPressed()
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
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.container_fragment, fragment, fragment.toString()).addToBackStack(fragment.toString()).commitAllowingStateLoss()
    }

    override fun popFragment() {
        supportFragmentManager.popBackStack()
    }

}
