package com.sametersoyoglu.kotlinnotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sametersoyoglu.kotlinnotesapp.databinding.ActivityMainBinding
import com.sametersoyoglu.kotlinnotesapp.db.NoteDatabase
import com.sametersoyoglu.kotlinnotesapp.repository.NoteRepository
import com.sametersoyoglu.kotlinnotesapp.viewmodel.NVMProviderFactory
import com.sametersoyoglu.kotlinnotesapp.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setUpViewModel()

    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository( NoteDatabase(this))

        val viewModelProviderFactory = NVMProviderFactory(application,noteRepository)

        noteViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)
    }



}