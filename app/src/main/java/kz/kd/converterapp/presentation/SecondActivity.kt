package kz.kd.converterapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.kd.converterapp.R

class SecondActivity : AppCompatActivity() {

    private lateinit var tbMain: Toolbar
    private lateinit var tbConfig: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bnvMain: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initViews()
        initToolbar()
        initNavigation()
    }

    private fun initViews() {
        tbMain = findViewById(R.id.tb_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        bnvMain = findViewById(R.id.bnv_main)
    }

    private fun initToolbar() {
        setSupportActionBar(tbMain)
    }

    private fun initNavigation() {
        navController = navHostFragment.navController
        tbConfig = AppBarConfiguration(navController.graph)
        tbMain.setupWithNavController(navController, tbConfig)
        bnvMain.setupWithNavController(navController)
    }
}