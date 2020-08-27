package dev.maxtrewartha.devibe.util

import org.w3c.dom.Document
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

object Util{

    public var port: Int = 7000
    public lateinit var topics: List<String>
    public var ip: String = "127.0.0.1"

    val endpoint: String = "https://pubsubhubbub.appspot.com/subscribe"
    val topic: String = "https://www.youtube.com/xml/feeds/videos.xml?channel_id="

    fun getIP(): String{
        return URL("https://api.ipify.org").readText()
    }

    fun readXml(input: String): Document {
        // Taken from https://www.hameister.org/KotlinXml.html
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(input)
        return doc
    }

}