package com.example.notesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.notesapp.fragments.AboutFragment
import com.example.notesapp.fragments.CreateNoteFragment
import com.example.notesapp.fragments.NotesFragment
import com.example.notesapp.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

// Main activity class for the application
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    // Method called when the activity is created
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Load default fragment (NotesFragment)
        loadFragment(NotesFragment())

        // Handle bottom navigation bar
        bottomNav.setOnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_notes -> NotesFragment() // No need to update adapter here
                R.id.nav_about -> AboutFragment()
                R.id.nav_settings -> SettingsFragment()
                else -> null
            }
            loadFragment(selectedFragment)
            true
        }

        // Handle drawer navigation
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.create_note -> loadFragment(CreateNoteFragment())
                R.id.view_notes -> loadFragment(NotesFragment()) // No need to update adapter here
                R.id.about -> loadFragment(AboutFragment())
            }
            drawerLayout.closeDrawers()
            true
        }

        // Setup toolbar to open the drawer
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }
    }

    // Method to load a fragment
    private fun loadFragment(fragment: Fragment?): Boolean {
        return fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, it)
                .commit()
            true
        } ?: false
    }
}
