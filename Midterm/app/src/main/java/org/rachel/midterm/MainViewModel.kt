package org.rachel.midterm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.rachel.midterm.`object`.Article
import org.rachel.midterm.`object`.Author
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun readData() {
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
                                    createdTime = document.data["createdTime"].toString().toLong(),
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
            articlesRetrieve.value = articleList.toList().sortedByDescending {
                it.createdTime
            }
        }
    }




}