package dev.maxtrewartha.devibe.util

import java.net.URL

val endpoint: String= "https://pubsubhubbub.appspot.com/subscribe"

class Requests {

    fun getIP(): String{
        return URL("https://api.ipify.org").readText()
    }


    /*
    A function to send a subscribe request to googles pubsubhubbub
     */
    fun subscribe(channel: String){

    }




}