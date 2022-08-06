package com.dnd.niceteam.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dnd.niceteam.R
import com.dnd.niceteam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as? NavHostFragment
        val navController = navHostFragment?.findNavController()
        navController?.let {
            binding.bnvMain.setupWithNavController(navController)
        }
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }
}