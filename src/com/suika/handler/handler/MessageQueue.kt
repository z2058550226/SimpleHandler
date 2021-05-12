package com.suika.handler.handler

import java.util.concurrent.DelayQueue

class MessageQueue {
    fun next(): Message? {
        return try {
            queue.take()
        } catch (e: InterruptedException) {
            e.printStackTrace()
            null
        }
    }

    fun enqueueMessage(msg: Message) {
        queue.add(msg)
    }

    private val queue: DelayQueue<Message> = DelayQueue()
}