package dev.maxtrewartha.devibe

import dev.maxtrewartha.devibe.util.Kaml
import dev.maxtrewartha.devibe.server.Server
import dev.maxtrewartha.devibe.util.Util
import kotlin.system.exitProcess

fun main(){

    // If checkFiles fails
    if(!Kaml().checkFiles()){
        println("Your config.yaml seems to be corrupted in some way, consider backing up and deleting it")
        exitProcess(0)
    } else {
        println("Config looks good! Loading up everything now...")
    }

    // Sets some variables like the ip, port, etc
    Util.config.port = Kaml().getConfig().port
    //Util.topics = Kaml().getConfig().topics
    Util.ip = Util.getIP()
    Util.config.webhook = Kaml().getConfig().webhook

    // A nice display to the user what IP they're gonna be using as a callback
    println("Your IP is: " + Util.ip + ", using this as your callback address")

    // Attempts to start the server before resubscription
    try{
        /*
        This starts the server thread to receive all of the post anf get requests from youtube
        */
        Server(Util.config.port).start()

    }finally {
        /*
        Resubscribes to current topics in the config
        */
        /*Kaml().getConfig().topics.forEach {
            Subscribe(it).run()
        }*/
    }

    /*
    An incredibly crude command system
     */
    while(true){
        val input: String? = readLine()
        val args : List<String> = input!!.split(" ")
        when(args[0]){
            "stop" -> exitProcess(0)
            "subscribe" -> {
                println("Subscribe not implemented yet!")
            }
            "unsubscribe" -> {
                println("Unsubscribe not implemented yet!")
            }
            "port" -> {
                println("Port swapping not implemented yet!")
            }
            "url" -> {
                if(args[1] == null){
                    println("No URL provided")
                    return
                }else if(args[1].startsWith("https://discordapp.com/api/webhooks/")){
                    println("This isn't a discord webhook...")
                    return
                }
                Util.config.webhook = args[1]
                Kaml().saveConfig()
            }
        }
    }
}
