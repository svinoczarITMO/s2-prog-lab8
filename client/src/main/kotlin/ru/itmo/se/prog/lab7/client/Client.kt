package ru.itmo.se.prog.lab7.client

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab7.client.di.notKoinModule
import ru.itmo.se.prog.lab7.client.utils.*
import ru.itmo.se.prog.lab7.client.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.client.utils.io.ReaderManager
import ru.itmo.se.prog.lab7.client.utils.validation.ClientValidator
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import java.io.File


fun main() {
    startKoin {
        modules(notKoinModule)
    }
    val commandManager = CommandManager()
    val clientValidator = ClientValidator()
    val write = PrinterManager()
    val read = ReaderManager()
    val message = Messages()
    val serializer = Serializer()
    val clientApp = ClientApp()
    val commandPackage = "ru.itmo.se.prog.lab7.client.commands"
    val kotlinIsBetterThanJava = true
    var loginFlag = false


    while (kotlinIsBetterThanJava) {
        while (!loginFlag) {
            write.linesInConsole(message.getMessage("login_info"))
            val flag = ::main.name
            write.inConsole("> ")
            val readFromConsole = (readln().lowercase()).split(" ").toMutableList()
            readFromConsole.add(flag)
            val command = commandManager.getCommand(commandPackage, readFromConsole[0], "Command")
            if (command != null && command.arg == ArgType.TOKEN) {
                val queue = clientValidator.validate(readFromConsole)
                for (data in queue) {
                    val dataStr = Json.encodeToString(data)
                    clientApp.request(dataStr)
                }
            }
        }

        val flag = ::main.name
        write.inConsole("> ")
        val readFromConsole = (readln().lowercase()).split(" ").toMutableList()
        readFromConsole.add(flag)
        val command = commandManager.getCommand(commandPackage, readFromConsole[0], "Command")
        if (command != null && command.status == StatusType.USER) {
            val queue = clientValidator.validate(readFromConsole)
            for (data in queue) {
                val dataStr = Json.encodeToString(data)
                clientApp.request(dataStr)
            }
        } else {
            write.linesInConsole(message.getMessage("weird_command"))
        }
    }
}