package com.yourdomain.tasklist

import android.app.Application
import com.google.firebase.FirebaseApp

class TaskListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
