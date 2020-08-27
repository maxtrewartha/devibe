package dev.maxtrewartha.devibe.runnable

import com.github.kittinunf.fuel.Fuel
import dev.maxtrewartha.devibe.util.Util

class Subscribe(val topic: String): Runnable{

    var url = Util.endpoint
    var payload = listOf("hub.callback" to "http://${Util.ip}:${Util.port}", "hub.topic" to "${Util.topic}${topic}", "hub.verify" to "sync", "hub.mode" to "subscribe", "hub.lease_seconds" to "864000")

    override fun run() {

        println("Subscribing to: ${topic}")

        // Send a post request to the endpoint
        val request = Fuel.post(Util.endpoint, payload).also {
            println(it.parameters)
        }


    }
}
