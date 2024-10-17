package com.example.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notesapp.R

// Définition de la classe AboutFragment qui étend Fragment
class AboutFragment : Fragment() {

    // Surcharge de la méthode onCreateView pour fournir l'UI du fragment
    override fun onCreateView(
        inflater: LayoutInflater, // LayoutInflater pour inflater le layout XML
        container: ViewGroup?, // ViewGroup optionnel auquel l'UI du fragment doit être attachée
        savedInstanceState: Bundle? // État de l'instance sauvegardé pour restaurer l'UI si nécessaire
    ): View? {
        // Inflater le layout pour ce fragment et retourner la View
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}
