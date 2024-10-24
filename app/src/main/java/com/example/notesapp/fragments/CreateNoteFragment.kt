package com.example.notesapp.fragments

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.adapters.MyAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 * Use the [CreateNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateNoteFragment : Fragment() {
    private lateinit var etNote:TextInputEditText
    private lateinit var btnSubmit:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_create_note, container, false)
        etNote=view.findViewById<TextInputEditText>(R.id.etNote)
        btnSubmit=view.findViewById<Button>(R.id.button)
        btnSubmit.setOnClickListener{
            saveNote()
        }
        return view
    }
    private fun saveNote(){
        val note=etNote.text.toString()
        if(note.isBlank()){
            Snackbar.make(requireView(),"Veuillez créer le note",Snackbar.LENGTH_SHORT).show()
            return
        }else{
            val notes=NotesFragment()
            val fragmentManager=requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_container,notes).commit()
            (parentFragment as? NotesFragment)?.addNote(note)
            Snackbar.make(requireView(),"Note ajouté avec succés",Snackbar.LENGTH_SHORT).show()

        }
    }


}