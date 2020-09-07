package dev.maxtrewartha.devibe.util

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File
import kotlin.system.exitProcess

class Kaml {



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

    fun saveConfig(){
        val file = File("config.yaml")
        if(!file.canWrite()){
            print("Unable to write to config :/")
            return
        }
        file.writeText(Yaml.default.encodeToString(Config.serializer(), Util.config))
        println("Saved config.")
    }

}

@Serializable
data class Config(
    var webhook: String,
    var port: Int,
    var useEveryone: Boolean
    //val topics: List<String>
)
