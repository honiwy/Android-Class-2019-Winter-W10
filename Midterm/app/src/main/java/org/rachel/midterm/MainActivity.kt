package org.rachel.midterm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.firestore.FirebaseFirestore
import org.rachel.midterm.databinding.ActivityMainBinding
import org.rachel.midterm.publish.PublishDialogFragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MidtermApplication.db = FirebaseFirestore.getInstance()
        val articles = MidtermApplication.db.collection("articles")

        val viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(articles)
        ).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.floatingActionButton.setOnClickListener {
            PublishDialogFragment().show(supportFragmentManager, "abc")
        }

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
