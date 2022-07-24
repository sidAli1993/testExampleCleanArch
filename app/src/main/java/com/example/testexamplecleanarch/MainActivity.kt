package com.example.testexamplecleanarch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testexamplecleanarch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var navController: NavController? = null
    private val _navController: NavController
        get() = navController!!

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController = navHostFragment.navController
        toolbar = binding.toolbar
        setEvents()
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
    override fun onBackPressed() {
        when (_navController.currentDestination?.id) {
            R.id.mainFragment -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
    fun setEvents(){
        binding.imvback.setOnClickListener {
            _navController.navigateUp()
        }
    }
}