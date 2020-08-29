package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.DiscordWebhook
import dev.maxtrewartha.devibe.Xml
import dev.maxtrewartha.devibe.util.Util
import java.io.File

class Pass(private val input: String): Runnable{
    override fun run() {

        val fileData = File("feed.xml")
        fileData.createNewFile()
        fileData.writeText(input)

        val feedData: Xml = Xml("feed.xml", "feed")
        val videoId: String = feedData.child("entry").child("yt:videoId").content()
        val videoTitle: String = feedData.child("entry").child("title").content()
        val videoAuthor: String = feedData.child("entry").child("author").child("name").content()
        println("$videoId / $videoTitle / $videoAuthor")

        // Sends the webhook
        var webhook: DiscordWebhook = DiscordWebhook(Util.webhook)
        webhook.setContent("**${videoAuthor}** just uploaded a new video *${videoTitle}*! Go check it out at https://www.youtube.com/watch?v=${videoId}")
        webhook.execute()

        // Deletes the file afterwards
        fileData.delete()

    }

}