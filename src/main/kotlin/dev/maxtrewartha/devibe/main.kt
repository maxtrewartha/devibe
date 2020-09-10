package dev.maxtrewartha.devibe

import dev.maxtrewartha.devibe.runnable.Subscribe
import dev.maxtrewartha.devibe.util.Kaml
import dev.maxtrewartha.devibe.server.Server
import dev.maxtrewartha.devibe.util.Util
import kotlin.system.exitProcess

fun main(){


    // Sets some variables like the ip, port, etc
    Kaml().getConfig()
    try {
        Util.ip = Util.getIP()
    } catch (e: Throwable) {
        println("Couldn't connect to ipify")
    }

    // The host variable used for changing port etc
    var host = Server(Util.config.port)
    host.go()

    // A nice display to the user what IP they're gonna be using as a callback
    println("Your IP is: " + Util.ip + ", using this as your callback address")


    fun reload(){
        Kaml().saveConfig()
        host.shutDown()
        host = Server(Util.config.port)
        host.go()
    }
    /*
    An incredibly crude command system
     */
    while(true){
        val input: String? = readLine()
        val args : List<String> = input!!.split(" ")
        when(args[0]){
            "stop" -> exitProcess(0)
            "sub" -> {
                // mmMMmm tasty formatting
                if (args.size == 2){ Subscribe(args[1]).run() }
                else { println("Too many args") }
            }
            "url" -> {
                if(args.size <= 1 && args[1].startsWith("https://discordapp.com/api/webhooks/")){
                    Util.config.webhook = args[1]
                    Kaml().saveConfig()
                }
            }
            "save" -> {
                Kaml().saveConfig()
            }
            "config" -> {
                println(" - Webhook: ${Util.config.webhook}\n - Port: ${Util.config.port}\n - Use Everyone: ${Util.config.useEveryone}")
            }
            "port" -> {
                if(args[1].toInt() is Int){
                    Util.config.port = args[1].toInt()
                    reload()
                }
            }
            "reload" -> {
                reload()
            }
        }
    }
}
