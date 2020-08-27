package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.util.Util
import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.util.ssl.SslContextFactory


class Subscribe(val topic: String): Runnable{

    override fun run() {

        println("Subscribing to: ${topic}")

        var url = Util.endpoint
        var payload = mapOf(
            "hub.callback" to "http://${Util.ip}:${Util.port}",
            "hub.topic" to "${Util.topic}${topic}",
            "hub.verify" to "sync",
            "hub.mode" to "subscribe",
            "hub.lease_seconds" to "864000"
        )

        // Send a post request to the endpoint
        try {

            val sslContextFactory = SslContextFactory.Client()
            val client = HttpClient(sslContextFactory)
            client.isFollowRedirects = true
            client.start()

            val response = client.POST(url)
                .param("hub.callback", "http://${Util.ip}:${Util.port}")
                .param("hub.topic", "${Util.topic}${topic}")
                .param("hub.verify", "sync")
                .param("hub.mode", "subscribe")
                .send()
            /*payload.forEach {
                response.param(it.key, it.value)
            }*/
            println(response.request)

        } catch (error: Throwable){
            println("Subscribe Failed")
            //exitProcess(0)
        }

    }
}
