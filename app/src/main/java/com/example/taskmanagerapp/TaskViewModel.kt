package com.yourdomain.tasklist.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yourdomain.tasklist.data.model.Task
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TaskViewModel : ViewModel() {
    private val db = Firebase.firestore.collection("tasks")
    val tasks = MutableLiveData<List<Task>>()

    fun currentUserId(): String = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    fun loadTasks(userId: String = currentUserId()) {
        viewModelScope.launch {
            val snapshot = db.whereEqualTo("userId", userId).get().await()
            tasks.value = snapshot.documents.map {
                it.toObject(Task::class.java)!!.copy(id = it.id)
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            db.add(task).await()
            loadTasks(task.userId)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            db.document(task.id).update("isCompleted", task.isCompleted).await()
            loadTasks(task.userId)
        }
    }
}