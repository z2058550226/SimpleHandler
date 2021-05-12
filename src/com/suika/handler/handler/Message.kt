package com.suika.handler.handler

import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

class Message : Delayed {
    var `when` = 0L
    var target: Handler? = null
    var callback: Runnable? = null

    fun recycleUnchecked() {
        `when` = 0
        target = null
        callback = null
    }

    override fun compareTo(other: Delayed): Int {
        return if (other is Message) {
            (`when` - other.`when`).toInt()
        } else {
            (getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS)).toInt()
        }
    }

    override fun getDelay(unit: TimeUnit): Long {
        return unit.convert((`when` - System.currentTimeMillis()), TimeUnit.MILLISECONDS)
    }
}