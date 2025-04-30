package com.yourdomain.tasklist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yourdomain.tasklist.R  // Added missing R import
import com.yourdomain.tasklist.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_list) as NavHostFragment
        val navController = navHost.navController

        // Fixed AppBarConfiguration constructor by explicitly using the Set<Int> overload
        val appBarConfig = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.taskListFragment, R.id.profileFragment)
        )

        // Fixed ambiguity by explicitly using AppBarConfiguration parameter
        setupActionBarWithNavController(navController, configuration = appBarConfig)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_list) as NavHostFragment
        return navHost.navController.navigateUp() || super.onSupportNavigateUp()
    }
}