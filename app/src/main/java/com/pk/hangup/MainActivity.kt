package com.pk.hangup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.pk.hangup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        navController = Navigation.findNavController(this,R.id.homeScreenFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home_navigation->{
                    if (navController.currentDestination?.id!=R.id.postViewFragment){
                        navController.navigate(R.id.postViewFragment)
                    }
                }
                R.id.menu_search_navigation->{
                    if(navController.currentDestination?.id!=R.id.searchFragment)
                    {
                        navController.navigate(R.id.searchFragment)
                    }
                }
                R.id.menu_notification_navigation->{
                    if (navController.currentDestination?.id!=R.id.notification)
                    {
                        navController.navigate(R.id.notification)
                    }
                }
                R.id.menu_profile_navigation->{
                    if (navController.currentDestination?.id!=R.id.userProfile)
                    {
                        navController.navigate(R.id.userProfile)
                    }
                }
            }
            true
        }
        navController.addOnDestinationChangedListener(
            NavController.OnDestinationChangedListener{ _nav: NavController,
                                                        navDestination: NavDestination, bundle: Bundle? ->
                when(navDestination.id){
                    R.id.postViewFragment->{
                        binding.bottomNavigationView.menu.findItem(R.id.menu_home_navigation).isChecked = true
                    }
                    R.id.notification->{
                        binding.bottomNavigationView.menu.findItem(R.id.menu_notification_navigation).isChecked=true
                    }
                    R.id.userProfile->{
                        binding.bottomNavigationView.menu.findItem(R.id.menu_profile_navigation).isChecked=true
                    }
                }
            })
    }
}