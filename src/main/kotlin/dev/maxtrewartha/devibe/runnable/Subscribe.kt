package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.util.Util
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class Subscribe(val topic: String): Runnable{

    // Makes a new OkHttp client
    var client: OkHttpClient = OkHttpClient()

    override fun run() {

        println("Subscribing to: ${topic}")

        // Make a form body for the query parameters
        var form = FormBody.Builder()
            .add("hub.callback", "http://${Util.ip}:${Util.port}")
            .add("hub.topic", "${Util.topic}${topic}")
            .add("hub.verify", "sync")
            .add("hub.mode", "subscribe")
            .add("hub.lease_seconds", "864000")
            .build()

        // Make a request with the parameters in it
        val request = Request.Builder()
            .url(Util.endpoint)
            .post(form)
            .build()

        // Finally call the request
        client.newCall(request).execute().use { response ->
            println(response.body!!.string())

            // If it's not successful

            if (!response.isSuccessful) throw IOException("Unexpected code $response")

        }


    }


}