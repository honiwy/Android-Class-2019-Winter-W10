package org.rachel.midterm

import android.app.Application
import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class MidtermApplication : Application() {
    companion object {
        lateinit var appContext: Context
        lateinit var db: FirebaseFirestore
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

    }
}