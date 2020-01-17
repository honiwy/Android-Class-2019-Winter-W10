package org.rachel.midterm.publish

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MessageDialog)
    }
//    override fun onStart() {
//        super.onStart()
//
//        val dialog = getDialog() as BottomSheetDialog
//        val bottomSheet =
//            dialog.delegate.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
//        bottomSheet?.let { view ->
//            view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
//        }
//        view?.let {
//            it.post {
//                kotlin.run {
//                    val parent = it.parent as View
//                    val params = parent.layoutParams as CoordinatorLayout.LayoutParams
//                    val behavior = params.behavior
//                    val bottomSheetBehavior = behavior as BottomSheetBehavior
//                    bottomSheetBehavior.peekHeight = it.measuredHeight
//                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//                }
//            }
//        }
//    }
}