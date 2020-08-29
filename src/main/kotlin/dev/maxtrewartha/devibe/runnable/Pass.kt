package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.DiscordWebhook
import dev.maxtrewartha.devibe.Xml
import dev.maxtrewartha.devibe.util.Util
import java.io.File
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime

class Pass(private val input: String): Runnable{
    override fun run() {

        val filename = "feed-${LocalDateTime.now()}.xml"

        // Makes a new file
        val fileData = File(filename)
        fileData.createNewFile()
        fileData.writeText(input)

        // Making an xml file off the file
        val feedData = Xml(filename, "feed")
        if (feedData.children("entry").isEmpty()){
            return
        }

        // Checks whether published is different to updated (7.5 seconds)
        val published: Instant = Instant.parse(feedData.child("entry").child("published").content())
        val updated: Instant = Instant.parse(feedData.child("entry").child("updated").content())

        val duration: Long = Duration.between(published, updated).toMillis()
        if(duration > 7500){
            return
        }

        // Creating values for the webhook
        val videoId: String = feedData.child("entry").child("yt:videoId").content()
        val videoTitle: String = feedData.child("entry").child("title").content()
        val videoAuthor: String = feedData.child("entry").child("author").child("name").content()
        println("$videoId / $videoTitle / $videoAuthor")

        // Sends the webhook
        val webhook = DiscordWebhook(Util.webhook)
        webhook.setContent("*${videoAuthor}* just uploaded a new video *${videoTitle}*! Go check it out at https://www.youtube.com/watch?v=${videoId}")
        webhook.execute()

        // Deletes the file afterwards
        //fileData.delete()

    }

}