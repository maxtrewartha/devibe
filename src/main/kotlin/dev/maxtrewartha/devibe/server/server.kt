package dev.maxtrewartha.devibe.server

import io.javalin.Javalin
import kotlinx.html.*


class Server(val port: Int=7000, val address: String = "127.0.0.1"): Thread() {

    override fun run() {
        println("${Thread.currentThread()} has run")
        val app = Javalin.create().start(port)

        app.get("/"){
            ctx -> ctx.result("Hello World")
        }

    }
}

fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            +"Hello from Ktor"
        }
    }
}