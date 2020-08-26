package dev.maxtrewartha.devibe.server

import io.javalin.Javalin

class Server(val port: Int=7000, val address: String = "127.0.0.1"): Thread() {

    override fun run() {
        println("Javalin on thread ${Thread.currentThread().id} has started")
        val app = Javalin.create(){it.showJavalinBanner=false}.start(port)

        app.get("/"){
            println(it.queryParamMap())
        }

    }
}