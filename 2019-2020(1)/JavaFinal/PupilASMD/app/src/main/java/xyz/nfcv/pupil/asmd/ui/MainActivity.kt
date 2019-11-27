package xyz.nfcv.pupil.asmd.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_main.*
import xyz.nfcv.pupil.asmd.R
import xyz.nfcv.pupil.asmd.adapter.MainPagerAdapter
import xyz.nfcv.pupil.asmd.ui.fragment.HomeNavFragment
import xyz.nfcv.pupil.asmd.ui.fragment.HomeNavFragment.Pages.*
import xyz.nfcv.pupil.asmd.ui.fragment.ProblemSettingsFragment

class MainActivity : AppCompatActivity(), View.OnClickListener, HomeNavFragment.OnPageSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {}
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerOpened(drawerView: View) {}
        })
        main_drawer.setScrimColor(Color.TRANSPARENT)
        main_icon.setOnClickListener(this)
        MainPagerAdapter(supportFragmentManager, main_viewPager)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.main_icon -> main_drawer.openDrawer(GravityCompat.START)
            else -> Toast.makeText(this, "todo", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPageChanged(page: HomeNavFragment.Pages) {
        when(page) {
            EXAM -> {
                main_icon.setImageResource(R.drawable.ic_edit_white)
                main_title.text = "测试"
                main_viewPager.setCurrentItem(0, false)
                main_drawer.closeDrawer(GravityCompat.START)
            }

            ANALYSIS -> {
                main_icon.setImageResource(R.drawable.ic_data_white)
                main_title.text = "分析"
                main_viewPager.setCurrentItem(1, false)
                main_drawer.closeDrawer(GravityCompat.START)
            }

            MANAGE -> {
                main_icon.setImageResource(R.drawable.ic_data_white)
                main_title.text = "管理"
                main_viewPager.setCurrentItem(2, false)
                main_drawer.closeDrawer(GravityCompat.START)
            }

        }
    }

    companion object {
        const val TAG = "MA"
    }
}