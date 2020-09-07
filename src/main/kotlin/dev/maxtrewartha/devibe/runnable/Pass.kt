package dev.maxtrewartha.devibe.runnable

import dev.maxtrewartha.devibe.util.DiscordWebhook
import dev.maxtrewartha.devibe.util.Xml
import dev.maxtrewartha.devibe.util.Util
import java.io.File
import java.time.Duration
import java.time.Instant

class Pass(private val input: String): Runnable{
    override fun run() {

        val filename = "feed.xml"

        // Makes a new file
        val fileData = File(filename)
        fileData.createNewFile()
        fileData.writeText(input)

        // Making an xml file off the file
        val feedData = Xml(filename, "feed")
        if (feedData.children("entry").isEmpty()){
            return
        }

        // This is the simplest (and probably worst) way of substringing these
        val publishedString: String = feedData.child("entry").child("published").content()
        val subbedPublishedString: String  = publishedString.substring(0, publishedString.length - 6) + ".00Z"

        val updatedString: String = feedData.child("entry").child("published").content()
        val subbedUpdatedString: String  = publishedString.substring(0, updatedString.length - 6) + ".00Z"

        // Checks whether published is different to updated (7.5 seconds)

        val published: Instant = Instant.parse(subbedPublishedString)
        val updated: Instant = Instant.parse(subbedUpdatedString)

        val duration: Long = Duration.between(published, updated).toMillis()
        if(duration > 7500){
            return
        }

        // Creating values for the webhook
        val videoId: String = feedData.child("entry").child("yt:videoId").content()
        val videoTitle: String = feedData.child("entry").child("title").content().trim()
        val videoAuthor: String = feedData.child("entry").child("author").child("name").content()
        println("$videoId / $videoTitle / $videoAuthor")

        // Sends the webhook
        val webhook = DiscordWebhook(Util.config.webhook)
        if(!Util.config.useEveryone){
            webhook.setContent("**${videoAuthor}** just uploaded a new video - $videoTitle -  Go check it out at https://www.youtube.com/watch?v=${videoId}")
        } else {
            webhook.setContent("**${videoAuthor}** just uploaded a new video - $videoTitle -  Go check it out at https://www.youtube.com/watch?v=${videoId} @everyone")
        }
        webhook.execute()

        // Deletes the file afterwards
        fileData.delete()

    }

}