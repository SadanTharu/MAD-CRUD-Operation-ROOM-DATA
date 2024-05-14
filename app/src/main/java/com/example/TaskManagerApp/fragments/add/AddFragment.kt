package com.example.TaskManagerApp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.TaskManagerApp.R
import com.example.TaskManagerApp.model.Note
import com.example.TaskManagerApp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val title = addTitle_et.text.toString()
        val content = addContent_et.text.toString()
        val priority = addPriority_et.text

        if(inputCheck(title, content, priority)){
            // Create Note Object
            val note = Note(
                0,
                title,
                content,
                Integer.parseInt(priority.toString())
            )
            // Add Data to Database
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, content: String, priority: Editable): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content) && priority.isEmpty())
    }
}