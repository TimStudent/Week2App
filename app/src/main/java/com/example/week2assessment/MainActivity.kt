package com.example.week2assessment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.week2assessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //

        //
        val navHost =
            supportFragmentManager.findFragmentById(R.id.container_frag) as NavHostFragment
        val navController = navHost.navController
        val appConfig = AppBarConfiguration(navController.graph)
        supportActionBar?.hide()
        setupActionBarWithNavController(navController, appConfig)
        binding.menuToolbar.setupWithNavController(navController)
    }
}
class SharedViewModel:ViewModel(){
    var importantPosition = ""
    var importantTitle = "placeholder"
    var importantCategory = "placeholder"
    var importantDescription = "placeholder"
    var importantDate = "placeholder"

    fun update(saveItem1:String, saveItem2:String, saveItem3: String, saveItem4: String) {
        importantTitle = saveItem1
        importantCategory = saveItem2
        importantDescription = saveItem3
        importantDate = saveItem4
    }
}

