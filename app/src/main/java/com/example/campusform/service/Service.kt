package com.example.campusform.service

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineExceptionHandler

object Api {


}

val listLiveData = MutableLiveData<List<String>>()


inline fun <T> LiveData<T>.bindNonNull(
    lifecycleOwner: LifecycleOwner,
    crossinline block: (T) -> Unit
) =
    observe(lifecycleOwner, Observer { it?.let(block) })

private val QuietCoroutineExceptionHandler =
    CoroutineExceptionHandler { _, t -> t.printStackTrace() }