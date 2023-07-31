package com.ivy.sampleproject.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CoroutineUtil {

    /**
     *  CoroutineUtil.main helps running a block of code in the main thread while performing
     *  a task in the background or in the CoroutinesUtil.IO scope.
     */
    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        work()
    }

    /**
     *  CoroutineUtil.io helps running a block of code in the io thread or in background thread,
     *  while in main thread or in CoroutineUtil.main scope.
     */
    fun io(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.IO).launch {
        work()
    }
}