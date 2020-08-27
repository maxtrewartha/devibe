package dev.maxtrewartha.devibe.util

object Util{

    public var port: Int = 7000
    public lateinit var topics: List<String>
    public var ip: String = "127.0.0.1"

    val endpoint: String = "https://pubsubhubbub.appspot.com/subscribe"
    val topic: String = "https://www.youtube.com/xml/feeds/videos.xml?channel_id="

}