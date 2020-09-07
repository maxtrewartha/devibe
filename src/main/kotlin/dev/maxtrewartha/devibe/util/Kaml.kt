package dev.maxtrewartha.devibe.util

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File
import kotlin.system.exitProcess

class Kaml {

    fun getConfig() {
        // Checks to make sure the files exist
        val file = File("config.yaml")
        // Makes a new default config file if it doesnt exist
        if(!file.exists()){
            try {
                file.createNewFile()
                file.writeText(Yaml.default.encodeToString(Config.serializer(), Util.config))
            } catch (e: Throwable) {
                println(e)
            }
        }
        // If the file cant be read..
        if(!file.canRead()){ println("Can't read the config file :/"); return}
        // Tries to write the current config
        try {
            Util.config = Yaml.default.decodeFromString(Config.serializer(), file.readText())
        } catch (e: Throwable) {
            println("Something went wrong :/"); println(e)
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
