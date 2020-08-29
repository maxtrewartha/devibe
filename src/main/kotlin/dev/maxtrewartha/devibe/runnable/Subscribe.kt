package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.util.Util
import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.util.ssl.SslContextFactory


class Subscribe(val topic: String): Runnable{

    /*
    Need to come back to this later and fix this
     */

    override fun run() {

        println("Subscribing to: ${Util.topic}${topic} for ${Util.ip}:${Util.port}")

        val url = Util.endpoint

        // Send a post request to the endpoint
        try {

            val sslContextFactory = SslContextFactory.Client()
            val client = HttpClient(sslContextFactory)
            client.isFollowRedirects = true
            client.start()

            /*
            TODO Make these requests work
             */
            client.newRequest(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .param("hub.callback", "http://${Util.ip}:${Util.port}")
                .param("hub.topic", "${Util.topic}${topic}")
                .param("hub.verify", "sync")
                .param("hub.mode", "subscribe")
                .send(){result -> println(result.request.headers)}

        } catch (error: Throwable){
            println("Subscribe Failed")
            //exitProcess(0)
        }

    }
}
