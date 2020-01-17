package org.rachel.midterm.publish

import android.util.Log
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.*
import org.rachel.midterm.`object`.Article
import org.rachel.midterm.`object`.Author
import java.util.*

class PublishViewModel : ViewModel() {

    var title = MutableLiveData<String>()
    var tag = MutableLiveData<String>()
    var content = MutableLiveData<String>()

    fun publishData(articles: CollectionReference) {
        val document = articles.document()
        // Create a new user with a first and last name
        val author1 = Author(email = "wayne@school.com.tw", id = "waynechen323", name = "AKA小安老師")
        val author2 = Author(email = "rachel@dot.com.tw", id = "dot123", name = "I'm Rachel")
        val data = Article(
            author1,
            content = content.value ?: "HA HA HA",
            createdTime = Calendar.getInstance().timeInMillis,
            id = document.id,
            title = title.value ?: "Someone forget enter title",
            tag = tag.value ?: ""
        )
        Log.i("HAHA","Data: $data")
        document.set(data)
    }

}