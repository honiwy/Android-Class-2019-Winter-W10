package org.rachel.midterm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.rachel.midterm.`object`.Article
import org.rachel.midterm.`object`.Author
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*


class MainViewModel(val articles: CollectionReference) : ViewModel() {

    var articlesRetrieve = MutableLiveData<List<Article>>()

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        readData()
    }

    fun readData(){
        val articleList = ArrayList<Article>()




        coroutineScope.launch {
            articles.get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val authorData = document.data["author"] as HashMap<*, *>
                            articleList.add(
                                Article(
                                    author = Author(
                                        name = authorData["name"].toString(),
                                        email = authorData["email"].toString(),
                                        id = authorData["id"].toString()
                                    ),
                                    content = document.data["content"].toString(),
                                    createdTime = SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Time(document.data["createdTime"].toString().toLong())),
                                    id = document.data["id"].toString(),
                                    tag = document.data["tag"].toString(),
                                    title = document.data["title"].toString()
                                )
                            )
                            Log.d("HAHA", document.id + " => " + document.data)
                        }

                    } else {
                        Log.w(
                            "HAHA",
                            "Error getting documents.",
                            task.exception
                        )
                    }
                }
                .await()
            articlesRetrieve.value = articleList.toList()
        }
    }


    private fun addData(articles: CollectionReference) {
        val document = articles.document()
        // Create a new user with a first and last name
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.com.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
            "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場",
            "content" to "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，" +
                    "但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言自己品味很奇怪，" +
                    "近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭造型，卻意外美出新境界。",
            "createdTime" to Calendar.getInstance().timeInMillis,
            "id" to document.id,
            "tag" to "Beauty"
        )
        document.set(data as Map<String, Any>)
        val data2 = hashMapOf(
            "author" to hashMapOf(
                "email" to "rachel@dot.com.tw",
                "id" to "dot123",
                "name" to "I'm Rachel"
            ),
            "title" to "百萬人相信了「火星人侵襲地球！」？",
            "content" to "1938年萬聖節前夕，當時在美國CBS電台當播音員的奧森·威爾斯(Orson Welles)在《空中水銀劇場》" +
                    "播出改編自小說《世界大戰》的廣播劇。節目交代了故事背景情節後，突然插入一段緊急新聞，" +
                    "廣播員表示剛目擊「隕石」墜落，即場報導「火星人侵襲地球！」，嘩，把火星人講得非常可怕……",
            "createdTime" to Calendar.getInstance().timeInMillis,
            "id" to document.id,
            "tag" to "Science"
        )
        document.set(data2 as Map<String, Any>)


    }

}