package org.rachel.midterm.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import org.rachel.midterm.MidtermApplication
import org.rachel.midterm.R
import org.rachel.midterm.databinding.PublishDialogBinding

class PublishDialogFragment : DialogFragment() {

    private val viewModel: PublishViewModel by lazy {
        ViewModelProviders.of(this).get(PublishViewModel::class.java)
    }//要用到的時候再創建才不會浪費記憶體資源

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val articles = MidtermApplication.db.collection("articles")
        val binding: PublishDialogBinding =
            DataBindingUtil.inflate(inflater, R.layout.publish_dialog, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        binding.buttonPublish.setOnClickListener {
            viewModel.publishData(articles)
            dismiss()
        }
        binding.buttonClose.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.MessageDialog)
    }
}