package com.suika.handler.handler

class Looper {
    companion object {
        private val THREAD_LOCAL = ThreadLocal<Looper>()

        fun prepare() {
            THREAD_LOCAL.set(Looper())
        }

        fun myLooper(): Looper? = THREAD_LOCAL.get()

        fun loop() {
            val me: Looper = myLooper() ?: throw RuntimeException("No looper")
            val queue = me.messageQueue
            while (true) {
                val msg: Message = queue.next() ?: return
                msg.target!!.dispatchMessage(msg)
                msg.recycleUnchecked()
            }
        }
    }

    val messageQueue = MessageQueue()
}