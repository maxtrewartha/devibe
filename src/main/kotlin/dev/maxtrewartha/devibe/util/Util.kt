package dev.maxtrewartha.devibe.util

import java.net.URL


object Util{

    var config: Config = Config(webhook = "", port = 7000)
    //var port: Int = 7000
    //lateinit var topics: List<String>
    var ip: String = "127.0.0.1"
    //var webhook: String = ""

    const val endpoint: String = "https://pubsubhubbub.appspot.com/subscribe"
    const val topic: String = "https://www.youtube.com/xml/feeds/videos.xml?channel_id="

    fun getIP(): String{
        return URL("https://api.ipify.org").readText()
    }

}
