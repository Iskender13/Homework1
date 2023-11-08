package com.example.homework12

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.homework12.data.local.Pref
import com.example.homework12.databinding.ActivityMainBinding
import com.example.homework12.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        if (!pref.isShow())
            navController.navigate(R.id.onBoardingFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,R.id.taskFragment3
            )
        )
        showToast("Hello!")
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination,  _->
            if (destination.id==R.id.onBoardingFragment){
                navView.isVisible=false
                supportActionBar?.hide()
            }else{
                navView.isVisible=true
                supportActionBar?.show()
            }
        }


    }
}