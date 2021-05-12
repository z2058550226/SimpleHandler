package com.suika.handler.handler

open class Handler(looper: Looper = Looper.myLooper()!!) {
    private val mQueue: MessageQueue = looper.messageQueue

    fun dispatchMessage(msg: Message) {
        val callback = msg.callback
        if (callback != null) {
            callback.run()
        } else {
            handleMessage(msg)
        }
    }

    fun sendMessage(msg: Message) {
        sendMessageDelayed(msg, 0L)
    }

    fun sendMessageDelayed(msg: Message, delay: Long) {
        val realDelay = delay.coerceAtLeast(0L)
        println("realDelay: $realDelay")
        msg.target = this
        msg.`when` = System.currentTimeMillis() + realDelay
        mQueue.enqueueMessage(msg)
    }

    fun post(r: Runnable) {
        postDelayed(r, 0L)
    }

    fun postDelayed(r: Runnable, delay: Long) {
        val msg = Message()
        msg.callback = r
        sendMessageDelayed(msg, delay)
    }

    open fun handleMessage(msg: Message) = Unit
}