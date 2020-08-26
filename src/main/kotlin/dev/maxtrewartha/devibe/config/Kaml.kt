package dev.maxtrewartha.devibe.config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.parse
import java.io.File

@Serializable
data class Config(
    val port: Int,
    val topics: List<String>
)

class Kaml {

    fun checkFiles(): Boolean{

        /*
        If config.yaml doesn't exist, we'll try and make one
         */
        if(!File("config.yaml").exists()){
            println("config.yaml doesn't exist, creating a new file...")
            // I'm gonna put file operations in try/catch blocks just in case something breaks
            return try {

                var file = File("config.yaml")
                file.createNewFile()
                file.writeText("port: 7000\n" + "topics: \n" + "  - UCQvkFJEo4iLVRF41gBKycmg\n" + "  - UCqgdc8k_kS0-x9XK8wQnVVg")
                true

            } catch (err: Throwable) {
                println(err)
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

}