package com.nigel.marks.wizsquared

import androidx.lifecycle.ViewModel
import com.nigel.marks.wizsquared.data.Repository

class MainViewModel : ViewModel() {
    val repository = Repository()
}