package com.topsecurity.preferences_datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

class Model : ViewModel() {
    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
    val EXAMPLE = stringPreferencesKey("example_counter")
    var counter: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getData(store: DataStore<Preferences>) {
        viewModelScope.launch {
            var exampleCounterFlow: Flow<Int> = store.data
                .map { preferences ->
                    Log.d("thinhvh", "preferences: ${preferences[EXAMPLE_COUNTER]}")
                    preferences[EXAMPLE_COUNTER] ?: -1
                }

            exampleCounterFlow.collect{value->
                counter.postValue(value)
            }
        }
    }

    fun putData(store: DataStore<Preferences>) {
        viewModelScope.launch {
            store.edit { settings ->
                settings[EXAMPLE_COUNTER] = Random.nextInt(1, 100)
            }
        }
    }
}