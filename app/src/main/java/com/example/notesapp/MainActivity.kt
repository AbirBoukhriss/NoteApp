package com.example.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.fragments.AboutFragment
import com.example.notesapp.fragments.CreateNoteFragment
import com.example.notesapp.fragments.NotesFragment
import com.example.notesapp.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

// Classe principale de l'application, qui hérite de AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout // Déclaration d'un tiroir de navigation, initialisé plus tard
    private lateinit var binding:ActivityMainBinding

    // Méthode appelée lors de la création de l'activité
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Appel de la méthode de la superclasse pour la création de l'activité
        setContentView(R.layout.activity_main) // Définir le layout principal de l'activité

        // Initialisation du tiroir de navigation en utilisant findViewById pour obtenir la vue correspondante
        drawerLayout = findViewById(R.id.drawer_layout)

        // Initialisation de la vue de navigation du tiroir
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        // Initialisation de la barre de navigation en bas (BottomNavigationView)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Charger le fragment par défaut (NotesFragment) lors du démarrage
        loadFragment(NotesFragment())

        // Écouteur d'événements pour gérer les sélections dans la barre de navigation par le bas
        bottomNav.setOnNavigationItemSelectedListener { item ->
            // Déterminer quel fragment charger en fonction de l'élément sélectionné
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_notes -> NotesFragment() // Si l'élément sélectionné est 'nav_notes', charger NotesFragment
                R.id.nav_about -> AboutFragment() // Si l'élément sélectionné est 'nav_about', charger AboutFragment
                R.id.nav_settings -> SettingsFragment() // Si l'élément sélectionné est 'nav_settings', charger SettingsFragment
                else -> null // Retourner null si aucun élément ne correspond
            }
            loadFragment(selectedFragment) // Charger le fragment sélectionné
            true // Indiquer que l'événement a été traité
        }

        // Écouteur d'événements pour gérer les sélections dans la vue de navigation du tiroir
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.create_note -> {
                    // Gérer la création d'une note (à implémenter)
                    loadFragment(CreateNoteFragment())
                }
                R.id.view_notes -> loadFragment(NotesFragment()) // Charger le fragment Notes lorsque 'view_notes' est sélectionné
                R.id.about -> loadFragment(AboutFragment()) // Charger le fragment About lorsque 'about' est sélectionné
            }
            drawerLayout.closeDrawers()  // Fermer le tiroir après une sélection
            true // Indiquer que l'événement a été traité
        }

        // Configurer la toolbar pour ouvrir le tiroir
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar) // Initialiser la toolbar
        setSupportActionBar(toolbar) // Définir la toolbar comme ActionBar pour l'activité
        toolbar.setNavigationIcon(R.drawable.ic_menu)  // Définir une icône pour le tiroir
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(navigationView)  // Ouvrir le tiroir lorsque l'icône est cliquée
        }
    }

    // Méthode pour charger un fragment
    private fun loadFragment(fragment: Fragment?): Boolean {
        return fragment?.let {
            // Si le fragment n'est pas nul, commencer une transaction de fragment
            supportFragmentManager.beginTransaction() // Démarrer une transaction pour gérer les fragments
                .replace(R.id.fragment_container, it) // Remplacer le fragment actuel par le nouveau fragment donné
                .commit() // Valider la transaction pour appliquer les changements
            true // Indiquer que le chargement du fragment a réussi
        } ?: false // Si le fragment est nul, retourner false
    }
}