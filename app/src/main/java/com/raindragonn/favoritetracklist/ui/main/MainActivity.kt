package com.raindragonn.favoritetracklist.ui.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.raindragonn.favoritetracklist.R
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.databinding.ActivityMainBinding
import com.raindragonn.favoritetracklist.ui.FavoriteFragment
import com.raindragonn.favoritetracklist.ui.track.TrackFragment
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener, CoroutineScope {
    private val job: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val repository: TrackRepository by inject()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity

            initBottomNavigationView()

            launch {
                repository.getTrackList().forEach {
                    Log.d("DEV_LOG", "onCreate: $it")
                }
            }
        }
    }

    private fun initBottomNavigationView() = with(binding) {
        bottomNav.setOnItemSelectedListener(this@MainActivity)
        bottomNav.selectedItemId = R.id.menu_track_list
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_track_list -> {
                replaceFragment(TrackFragment())
                true
            }
            R.id.menu_favorite_list -> {
                replaceFragment(FavoriteFragment())
                true
            }
            else -> false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(
                    binding.flContainer.id,
                    fragment
                )
            }.commitAllowingStateLoss()
    }
}