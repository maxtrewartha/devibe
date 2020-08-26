package dev.maxtrewartha.devibe

import dev.maxtrewartha.devibe.util.Config
import dev.maxtrewartha.devibe.util.Kaml
import dev.maxtrewartha.devibe.server.Server
import kotlin.system.exitProcess

fun main(){

    var config: Config

    // If checkFiles fails
    if(!Kaml().checkFiles()){
        println("Your config.yaml seems to be corrupted in some way, consider backing up and deleting it")
        exitProcess(0)
    } else {
        println("Config looks good! Loading up everything now...")
    }

    /*
     This starts the server thread to receive all of the post anf get requests from youtube
     */
    Server().start()

    /*
    An incredibly crude command system
     */
    while(true){
        var input: String? = readLine()
        var args : List<String> = input!!.split(" ")
        when(args[0]){
            "stop" -> exitProcess(0)

        }
    }


}