package com.raindragonn.favoritetracklist.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.raindragonn.favoritetracklist.R
import com.raindragonn.favoritetracklist.databinding.ActivityMainBinding
import com.raindragonn.favoritetracklist.ui.favorite.FavoriteFragment
import com.raindragonn.favoritetracklist.ui.track.TrackFragment
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    // ViewModel
    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            initBottomNavigationView()

            vm.loadList()
        }
    }

    private fun initBottomNavigationView() = with(binding) {
        bottomNav.setOnItemSelectedListener(this@MainActivity)
        bottomNav.selectedItemId = R.id.menu_track_list
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_track_list -> {
                replaceFragment(TrackFragment.newInstance(), TrackFragment.TAG)
                true
            }
            R.id.menu_favorite_list -> {
                replaceFragment(FavoriteFragment.newInstance(), FavoriteFragment.TAG)
                true
            }
            else -> false
        }
    }

    /**
     * 프래그먼트 변경 기능
     * 기존 프래그먼트가 없는경우 새로 추가하며
     * 프래그먼트가 있는경우 다른 프래그먼트를 숨기고 보여줍니다.
     */
    private fun replaceFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }

        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fl_container, fragment, tag)
                .commitAllowingStateLoss()
        }
    }
}