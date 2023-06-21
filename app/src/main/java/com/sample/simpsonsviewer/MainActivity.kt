package com.sample.simpsonsviewer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sample.simpsonsviewer.databinding.ActivityMainBinding
import com.sample.simpsonsviewer.fragments.ListFragment
import com.sample.simpsonsviewer.viewModels.DuckDuckGoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private val viewModel: DuckDuckGoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = findViewById<View>(R.id.nav_host_fragment)
        if (navHostFragment != null) {
            // Phone
            setupActionBarWithNavController(navController)
        } else {
            // Tablet
            val listContainer = findViewById<View>(R.id.list_container)
            if (listContainer != null && savedInstanceState == null) {
                val listFragment = ListFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.list_container, listFragment)
                    .commit()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

