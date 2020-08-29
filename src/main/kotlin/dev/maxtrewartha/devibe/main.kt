package dev.maxtrewartha.devibe

import dev.maxtrewartha.devibe.runnable.Subscribe
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
    Util.port = Kaml().getConfig().port
    Util.topics = Kaml().getConfig().topics
    Util.ip = Util.getIP()

    // A nice display to the user what IP they're gonna be using as a callback
    println("Your IP is: " + Util.ip + ", using this as your callback address")

    // Attempts to start the server before resubscription
    try{
        /*
        This starts the server thread to receive all of the post anf get requests from youtube
        */
        Server(Util.port, Util.ip).start()

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
        var input: String? = readLine()
        var args : List<String> = input!!.split(" ")
        when(args[0]){
            "stop" -> exitProcess(0)
            "subscribe" -> {
                println("Subscribe not implemented yet!")
            }
            "unsub" -> {}

        }
    }


}