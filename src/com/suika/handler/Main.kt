package com.suika.handler

import com.suika.handler.handler.Handler
import com.suika.handler.handler.Looper
import com.suika.handler.handler.Message
import kotlin.concurrent.thread

fun main() {
    Looper.prepare()

    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            println("<top>.handleMessage, msg: $msg")
        }
    }

    thread {
        handler.postDelayed({
            println("delayed")
        }, 15000L)
//        repeat(5) {
//            Thread.sleep(1000L)
//            handler.post {
//                println("in post")
//            }
//        }
    }

    Looper.loop()

    println("after loop")
}