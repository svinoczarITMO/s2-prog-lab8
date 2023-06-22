package ru.itmo.se.prog.lab7.client.app

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.LocationType

class ExecuteServerCommand {
    fun run (commandName: String, args: MutableMap<String, String>, flag: String = "main"): Data {
        val paramList = mutableListOf(commandName, args, flag)
        val data = MyApp.guiClientValidator.validate(paramList)
        val dataStr = Json.encodeToString(data)
        return if (data.locationType == LocationType.SERVER) {
            MyApp.di.clientApp.request(dataStr)
        } else {
            data
        }
    }
}