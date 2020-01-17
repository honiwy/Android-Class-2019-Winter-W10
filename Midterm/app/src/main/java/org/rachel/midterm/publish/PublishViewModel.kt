package org.rachel.midterm.publish

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import org.rachel.midterm.`object`.Article
import org.rachel.midterm.`object`.Author
import java.util.*

class PublishViewModel : ViewModel() {

    var title = MutableLiveData<String>()
    var tag = MutableLiveData<String>()
    var content = MutableLiveData<String>()
    var isWayne = MutableLiveData<Boolean>()
    private val author1 = Author(email = "wayne@school.com.tw", id = "waynechen323", name = "AKA小安老師")
    private val author2 = Author(email = "rachel@dot.com.tw", id = "dot123", name = "I'm Rachel")
    lateinit var author: Author

    init {
        isWayne.value = false
    }

    fun publishData(articles: CollectionReference) {
        val document = articles.document()
        // Create a new user with a first and last name
        author = if (isWayne.value == true) {
            author1
        } else
            author2
        val data = Article(
            author,
            content = content.value ?: "HA HA HA",
            createdTime = Calendar.getInstance().timeInMillis,
            id = document.id,
            title = title.value ?: "Someone forget enter title",
            tag = tag.value ?: ""
        )
        Log.i("HAHA", "Wayne?: ${isWayne.value},  Data: $data")
        document.set(data)
    }

}