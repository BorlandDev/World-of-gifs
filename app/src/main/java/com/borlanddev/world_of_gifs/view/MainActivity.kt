package com.borlanddev.world_of_gifs.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.borlanddev.world_of_gifs.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    // Реализация кнопки вверх
    override fun onSupportNavigateUp(): Boolean {
      return navController.navigateUp() || super.onSupportNavigateUp()
    }

}