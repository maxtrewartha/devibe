package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.Xml
import java.io.File

class Pass(val input: String): Runnable{
    override fun run() {

        val fileData = File("feed.xml")
        fileData.createNewFile()
        fileData.writeText(input)

        val feedData: Xml = Xml("feed.xml", "feed")
        val videoId: String = feedData.child("entry").child("yt:videoId").content()
        val videoTitle: String = feedData.child("entry").child("title").content()
        val videoAuthor: String = feedData.child("entry").child("author").child("name").content()
        println("$videoId / $videoTitle / $videoAuthor")

        fileData.delete()

    }

}