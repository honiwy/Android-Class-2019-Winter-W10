package org.rachel.midterm

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import org.rachel.midterm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }//要用到的時候再創建才不會浪費記憶體資源
    lateinit var db: FirebaseFirestore
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
        val articles = db.collection("articles")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = ArticleAdapter()
        val haha = viewModel.readData(articles)
        Log.i("HAHA","List of article: $haha")
        binding.recyclerView.adapter = adapter

//        viewModel.articles.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })





    }


}
