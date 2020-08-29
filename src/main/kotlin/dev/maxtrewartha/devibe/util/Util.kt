package dev.maxtrewartha.devibe.util

import java.net.URL


object Util{

    var port: Int = 7000
    lateinit var topics: List<String>
    var ip: String = "127.0.0.1"

    const val endpoint: String = "https://pubsubhubbub.appspot.com/subscribe"
    const val topic: String = "https://www.youtube.com/xml/feeds/videos.xml?channel_id="
    const val webhook: String = "https://discordapp.com/api/webhooks/749248724877967391/oX8VLxJ4qENtuw5mbn9UrMD3XpKkKHR7YZWq8oCpYo2n3EPRYNq_Na0kEf39b8Mw8zJP"

    fun getIP(): String{
        return URL("https://api.ipify.org").readText()
    }



}
