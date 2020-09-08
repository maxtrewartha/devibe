package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.util.Util
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection


class Subscribe(private val topic: String): Runnable{

    /*
    Need to come back to this later and fix this
     */

    override fun run() {

        println("Subscribing to: $topic for ${Util.ip}:${Util.config.port}...")

        // First make the request parameters
        var reqParam = URLEncoder.encode("hub.callback", "UTF-8") + "=" + URLEncoder.encode("${Util.ip}:${Util.config.port}", "UTF-8")
        reqParam += "&" + URLEncoder.encode("hub.topic", "UTF-8") + "=" + URLEncoder.encode("${Util.topic}+$topic", "UTF-8")
        reqParam += "&" + URLEncoder.encode("hub.verify", "UTF-8") + "=" + URLEncoder.encode("sync", "UTF-8")
        reqParam += "&" + URLEncoder.encode("hub.mode", "UTF-8") + "=" + URLEncoder.encode("subscribe", "UTF-8")

        val connection = URL(Util.endpoint)

        try {
            with(connection.openConnection() as HttpsURLConnection) {
                requestMethod = "POST"
                doOutput = true
                doInput = true

                val write = OutputStreamWriter(outputStream)
                write.write(reqParam)
                write.flush()

                println("URL : $url?$reqParam")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    println("Response : $response")
                }

            }
        } catch (e: Throwable) {
            println("Couldn't subscribe :/")
        }
    }
}
