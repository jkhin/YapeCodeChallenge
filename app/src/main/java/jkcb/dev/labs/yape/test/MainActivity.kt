package jkcb.dev.labs.yape.test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavDestination
import jkcb.dev.labs.yape.test.databinding.ActivityMainBinding
import jkcb.dev.labs.yape.test.utils.handleVisibility

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupView() {
        setupActionBar()
        setupViewEvents()
    }

    private fun setupViewEvents() {
        findNavController(R.id.fragmentContainerView)
            .addOnDestinationChangedListener { _, destination, _ ->
                handleAppBarVisibility(destination)
                handleFabVisibility(destination)
            }

        binding.fab.setOnClickListener {
            val intentContact = Intent(Intent.ACTION_VIEW)
            intentContact.data = Uri.parse(BuildConfig.WA_ME_URL)
            startActivity(intentContact)
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun handleAppBarVisibility(destination: NavDestination) {
        val isNeededToHide = when (destination.id) {
            R.id.recipesFragment -> true
            else -> false
        }
        supportActionBar?.apply { if (isNeededToHide) hide() else show() }
    }

    private fun handleFabVisibility(destination: NavDestination) {
        val isNeededToHide = when (destination.id) {
            R.id.recipesFragment,
            R.id.detailRecipeFragment-> true
            else -> false
        }
        binding.fab.handleVisibility(isNeededToHide)

    }
}