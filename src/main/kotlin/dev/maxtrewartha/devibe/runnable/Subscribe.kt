package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.util.Util
import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.util.ssl.SslContextFactory


class Subscribe(val topic: String): Runnable{

    /*
    Need to come back to this later and fix this
     */

    override fun run() {

        println("Subscribing to: ${Util.topic}${topic} for ${Util.ip}:${Util.config.port}")

    }
}
