package org.rachel.midterm

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.sql.Time
import java.text.SimpleDateFormat

@BindingAdapter("dateFormat")
fun bindTime(textView: TextView, calendarTime: Long) {
    textView.text = SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Time(calendarTime))
}