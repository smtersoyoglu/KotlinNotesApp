package com.sametersoyoglu.kotlinnotesapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.sametersoyoglu.kotlinnotesapp.MainActivity
import com.sametersoyoglu.kotlinnotesapp.R
import com.sametersoyoglu.kotlinnotesapp.databinding.FragmentUpdateNoteBinding
import com.sametersoyoglu.kotlinnotesapp.model.Note
import com.sametersoyoglu.kotlinnotesapp.viewmodel.NoteViewModel


class UpdateNoteFragment : Fragment() {
    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var  currentNote: Note
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel

        currentNote = args.note!!

        binding.noteTitleUpdate.setText(currentNote.noteTitle)
        binding.noteBodyUpdate.setText(currentNote.noteBody)

        binding.fabUpdate.setOnClickListener {

            val title = binding.noteTitleUpdate.text.toString().trim()
            val body = binding.noteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {

                val note = Note(currentNote.id,title,body)

                noteViewModel.updateNote(note)

                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            else {
                Toast.makeText(context,"Lütfen başlık adını giriniz!", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Notu Sil")
            setMessage("Bu notu silmek istediğinizden emin misiniz?")
            setPositiveButton("Sil"){_,_ ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }

            setNegativeButton("İptal Et",null)
        }.create().show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete_menu -> deleteNote()
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}