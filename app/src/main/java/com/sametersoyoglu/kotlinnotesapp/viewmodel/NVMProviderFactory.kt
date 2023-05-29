package com.sametersoyoglu.kotlinnotesapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sametersoyoglu.kotlinnotesapp.repository.NoteRepository

class NVMProviderFactory (val app: Application,
    private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository) as T
    }

    }