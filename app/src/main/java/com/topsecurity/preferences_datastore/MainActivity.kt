package com.topsecurity.preferences_datastore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.*

class MainActivity : AppCompatActivity() {
    val model: Model by viewModels<Model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.getData(dataStore)

        model.counter.observe(this){
            Log.d("thinhvh", "onCreate: $it")
        }
    }
}

val Context.dataStore by preferencesDataStore(name = "settings")
val Context.dataStore2 by preferencesDataStore(name = "settings2")