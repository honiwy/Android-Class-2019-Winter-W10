package org.rachel.midterm

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import org.rachel.midterm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance()

        val articles = db.collection("articles")

        val viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(articles)
        ).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        val adapter = ArticleAdapter()

        binding.recyclerView.adapter = adapter

        viewModel.articlesRetrieve.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


    }


}
