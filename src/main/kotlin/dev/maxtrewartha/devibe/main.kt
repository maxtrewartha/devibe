package dev.maxtrewartha.devibe

import dev.maxtrewartha.devibe.config.Kaml
import dev.maxtrewartha.devibe.server.Server

fun main(){

    Kaml().checkConfig()


    /*
     This starts the server thread to receive all of the post anf get requests from youtube
     */
    //Server().start()

}