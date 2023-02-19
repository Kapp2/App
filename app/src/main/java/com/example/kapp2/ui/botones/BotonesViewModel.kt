package com.example.kapp2.ui.botones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BotonesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is botones Fragment"
    }
    val text: LiveData<String> = _text
}