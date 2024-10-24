package com.example.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.adapters.MyAdapter

class NotesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val notesList = mutableListOf("Note 1", "Note 2", "Note 3") // Sample notes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        recyclerView = view.findViewById(R.id.item_view) // Make sure the RecyclerView ID matches
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(notesList)
        recyclerView.adapter = adapter

        return view
    }
    fun addNote(note: String) {
        notesList.add(note)
        updateAdapterData(notesList)
    }
    private fun updateAdapterData(list: List<String>){
        adapter= MyAdapter(list)
        recyclerView.adapter=adapter
    }
}
