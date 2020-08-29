package dev.maxtrewartha.devibe.util

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File
import kotlin.system.exitProcess

class Kaml {

    fun checkFiles(): Boolean{

        /*
        If config.yaml doesn't exist, we'll try and make one
         */
        if(!File("config.yaml").exists()){
            println("Your config.yaml doesn't exist, creating a new file...")
            // I'm gonna put file operations in try/catch blocks just in case something breaks
            return try {

                val file = File("config.yaml")
                file.createNewFile()
                file.writeText("webhook: https://discordwebhook\n" + "port: 7000\n")
                true

            } catch (error: Throwable) {
                println(error)
                false
            }
        }
        /*
        If it does, we try and load the data
         */
        else {

            val file = File("config.yaml")
            return try{
                Yaml.default.decodeFromString(Config.serializer(), file.readText())
                true
            } catch (err: Throwable){
                println(err)
                false
            }

        }

    }

    fun getConfig(): Config{
        // Checks to make sure the files exist
        checkFiles()
        try{
            // Gets the file and returns the config
            val file = File("config.yaml")
            return Yaml.default.decodeFromString(Config.serializer(), file.readText())
        } // If anything goes wrong..
        catch (err: Throwable){
            println("Something went wrong with getting the config file.")
            println(err)
            exitProcess(0)
        }
    }

}

@Serializable
data class Config(
    val webhook: String,
    val port: Int,
    //val topics: List<String>
)
