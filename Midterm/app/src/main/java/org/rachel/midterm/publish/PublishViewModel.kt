package org.rachel.midterm.publish

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

    private val _publishedArticle = MutableLiveData<Article>()
    val publishedArticle: LiveData<Article>
        get() = _publishedArticle

    var title = MutableLiveData<String>()
    var tag = MutableLiveData<String>()
    var content = MutableLiveData<String>()


    init {
        title.value = ""
        tag.value = ""
        content.value = ""
    }

    fun publishData(articles: CollectionReference) {
        val document = articles.document()
        // Create a new user with a first and last name
        val author1 = Author(email = "wayne@school.com.tw", id = "waynechen323", name = "AKA小安老師")
        val data = Article(
            author1,
            content = content.value ?: "HA HA HA",
            createdTime = Calendar.getInstance().timeInMillis,
            id = document.id,
            title = title.value ?: "Someone forget enter title",
            tag = tag.value ?: ""
        )
        document.set(data as Map<String, Any>)
//        val data2 = hashMapOf(
//            "author" to hashMapOf(
//                "email" to "rachel@dot.com.tw",
//                "id" to "dot123",
//                "name" to "I'm Rachel"
//            ),
//            "title" to "百萬人相信了「火星人侵襲地球！」？",
//            "content" to "1938年萬聖節前夕，當時在美國CBS電台當播音員的奧森·威爾斯(Orson Welles)在《空中水銀劇場》" +
//                    "播出改編自小說《世界大戰》的廣播劇。節目交代了故事背景情節後，突然插入一段緊急新聞，" +
//                    "廣播員表示剛目擊「隕石」墜落，即場報導「火星人侵襲地球！」，嘩，把火星人講得非常可怕……",
//            "createdTime" to Calendar.getInstance().timeInMillis,
//            "id" to document.id,
//            "tag" to "Science"
//        )
//        document.set(data2 as Map<String, Any>)
    }

}