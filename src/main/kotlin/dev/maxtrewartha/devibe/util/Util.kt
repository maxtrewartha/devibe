package dev.maxtrewartha.devibe.util

import dev.maxtrewartha.devibe.server.Server
import java.net.URL

object Util{

    var config: Config = Config(webhook = "https://discord.com/api/webhooks/", port = 58410, useEveryone = false)
    var ip: String = "127.0.0.1"

    const val endpoint: String = "https://pubsubhubbub.appspot.com/subscribe"
    const val topic: String = "https://www.youtube.com/xml/feeds/videos.xml?channel_id="

    fun getIP(): String{
        return URL("https://api.ipify.org").readText()
    }

}
