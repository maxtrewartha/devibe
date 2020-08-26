package dev.maxtrewartha.devibe.config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import java.io.File

@Serializable
data class Config(
    val port: Int,
    val topics: List<String>
)

class Kaml {

    /*
    This is to make sure that the config file exists
     */
    fun checkConfig() {
        /*
        File operations are done in catches just in case something goes wrong.
        Idk if you have to do it in Kotlin but hey-ho
         */
        if(!File("config.yaml").exists()){
            println("config.yaml doesn't exist, would you like to make one? [y/N]")
            // If you wanna make a dummy file
            val ans: String? = readLine()

            // Intellij said this would be a good idea
            if (ans != null) {
                // If they say yes to making a new file
                if (ans.toLowerCase() == "y") {
                    val config = File("config.yaml")
                    config.createNewFile()
                    config.writeText("port: 7000\n" + "topics: \n" + "  - UCQvkFJEo4iLVRF41gBKycmg\n" + "  - UCqgdc8k_kS0-x9XK8wQnVVg")
                }
            } else {
                println("You will have to configure devibe manually")
            }
        }
    }

}