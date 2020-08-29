package dev.maxtrewartha.devibe.server

import dev.maxtrewartha.devibe.Xml
import dev.maxtrewartha.devibe.runnable.Pass
import io.javalin.Javalin
import java.beans.Beans
import java.beans.XMLDecoder

class Server(val port: Int, val address: String): Thread() {

    override fun run() {
        //println("Javalin on thread ${Thread.currentThread().id} has started")
        val app = Javalin.create(){it.showJavalinBanner=false}.start(port)

        /*
        All of this should work
         */
        app.get("/"){ ctx ->
            var challenge = ctx.queryParamMap()["hub.challenge"]?.get(0).toString()
            println("GET REQUEST from ${ctx.ip()} with challenge: $challenge")
            ctx.html(challenge)
            ctx.status(202)
        }

        app.post("/"){
            println("POST REQUEST from ${it.ip()}")
            var input: String = it.body().trim().format("UTF-8")
            Pass(input).run()
        }

    }
}