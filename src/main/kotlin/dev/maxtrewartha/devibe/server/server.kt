package dev.maxtrewartha.devibe.server

import io.javalin.Javalin

class Server(val port: Int, val address: String): Thread() {

    override fun run() {
        println("Javalin on thread ${Thread.currentThread().id} has started")
        val app = Javalin.create(){it.showJavalinBanner=false}.start(port)

        /*
        All of this should work
         */
        app.get("/"){ctx ->
            var challenge = ctx.queryParamMap()["hub.challenge"]?.get(0).toString()
            println("GET REQUEST from ${ctx.ip()} with challenge: $challenge")
            ctx.html(challenge)
            ctx.status(202)
        }

        // TODO add discord webhookness to the post request
        app.post("/"){
            println("POST REQUEST from ${it.ip()}")
            var input: String = it.body().trim().format("UTF-8")
            // TODO make this parse xml without throwing an error
        }


    }
}