package com.excalibur.funwithlist.pack5

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.*

interface ActionTask {
    fun <T> sync(
        doIn: () -> T,
        doOut: (T) -> Unit = {},
        dispatcherIn: CoroutineDispatcher = Dispatchers.IO,
        dispathcherOut: CoroutineDispatcher = Dispatchers.Unconfined
    ): Job {
        return GlobalScope.launch(dispatcherIn) {
            val data = doIn()
            withContext(dispathcherOut) {
                doOut(data)
            }
        }
    }

    fun delay(time: Long, doAction: () -> Unit): Handler {
        return Handler(Looper.getMainLooper()).apply {
            postDelayed(Runnable(doAction), time)
        }
    }

    fun cancelDelay(handler: Handler?) {
        handler?.removeCallbacksAndMessages(null)
    }
}