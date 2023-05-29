package com.sametersoyoglu.kotlinnotesapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sametersoyoglu.kotlinnotesapp.MainActivity
import com.sametersoyoglu.kotlinnotesapp.R
import com.sametersoyoglu.kotlinnotesapp.databinding.FragmentNoteBinding
import com.sametersoyoglu.kotlinnotesapp.model.Note
import com.sametersoyoglu.kotlinnotesapp.viewmodel.NoteViewModel


class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        mView = view
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.noteTitle.text.toString().trim()
        val noteBody = binding.noteBody.text.toString().trim()

        if (noteTitle.isNotEmpty()) {

            val note = Note(0,noteTitle,noteBody)

            noteViewModel.addNote(note)
            Snackbar.make(view,"Not başarıyla kaydedildi",Snackbar.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_noteFragment_to_homeFragment)
        }
        else {
            Toast.makeText(context,"Lütfen not başlığını girin!",Toast.LENGTH_SHORT).show()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.save_menu -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.new_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}