package org.rachel.midterm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.CollectionReference

class ViewModelFactory(private val articles: CollectionReference) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(articles) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}