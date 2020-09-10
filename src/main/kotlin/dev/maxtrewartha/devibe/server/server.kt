package dev.maxtrewartha.devibe.server

import dev.maxtrewartha.devibe.runnable.Pass
import io.javalin.Javalin

class Server(private val port: Int): Thread() {

    //println("Javalin on thread ${Thread.currentThread().id} has started")
    val app = Javalin.create {it.showJavalinBanner=false}.start(port)

    fun go() {
        /*
        All of this should work
         */
        app.get("/"){ ctx ->
            val challenge = ctx.queryParamMap()["hub.challenge"]?.get(0).toString()
            println("GET REQUEST from ${ctx.ip()} with challenge: $challenge")
            ctx.html(challenge)
            ctx.status(202)
        }

        app.post("/"){
            println("POST REQUEST from ${it.ip()}")
            val input: String = it.body().trim().format("UTF-8")
            Pass(input).run()
            it.status(202)
        }

    }

    fun shutDown() {
        app.stop()
    }
}