<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/nav_graph"
app:startDestination="@id/loginFragment">

    <fragment
android:id="@+id/loginFragment"
android:name="com.example.taskmanagerapp.LoginFragment"
android:label="Iniciar Sesión"
tools:layout="@layout/fragment_login">
        <action
android:id="@+id/action_loginFragment_to_registerFragment"
app:destination="@id/registerFragment" />
        <action
android:id="@+id/action_loginFragment_to_taskListFragment"
app:destination="@id/taskListFragment"
app:popUpTo="@id/loginFragment"
app:popUpToInclusive="true" />
    </fragment>

    <fragment
android:id="@+id/registerFragment"
android:name="com.example.taskmanagerapp.RegisterFragment"
android:label="Registrarse"
tools:layout="@layout/fragment_register">
        <action
android:id="@+id/action_registerFragment_to_taskListFragment"
app:destination="@id/taskListFragment"
app:popUpTo="@id/loginFragment"
app:popUpToInclusive="true" />
    </fragment>

    <fragment
android:id="@+id/taskListFragment"
android:name="com.example.taskmanagerapp.TaskListFragment"
android:label="Lista de Tareas"
tools:layout="@layout/fragment_task_list" />
</navigation>