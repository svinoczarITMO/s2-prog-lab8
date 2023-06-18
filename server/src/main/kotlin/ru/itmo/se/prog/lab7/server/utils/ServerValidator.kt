package ru.itmo.se.prog.lab7.server.utils


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.utils.managers.CommandManager
import java.io.File
import java.util.*

class ServerValidator : KoinComponent {
    private val commandManager: CommandManager by inject()
    private val commandPackage = "ru.itmo.se.prog.lab7.server.commands"
    private val historyFile = File("D:\\ITMO\\2nd-semester\\prog-labs\\s2-prog-lab8\\common\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab7\\common\\data\\history.log")
    private val commandBuffer = historyFile.readLines().toMutableList()
    private val nullData = Data("null", "null", Person(
        0, "whoami", Coordinates(1f,1f), Date(),
        999, 999, Color.BROWN, Country.CHINA, Location(1,2,3), -1), "",
        User(0, "login", "psw", false), "main", ArgType.NO_ARG, StatusType.USER, LocationType.SERVER, "")

    fun validate (data: Data): Data? {
        val commandName = data.name
        commandBuffer.add(commandName)
        println(commandBuffer)

        historyFile.delete()
        historyFile.writeText(commandBuffer.joinToString())

        val result = commandManager.getCommand(commandPackage, data.name, "Command")?.execute(data)
        println(result!!.answerStr)
        return if (data.locationType == LocationType.SERVER) {
            result
        } else {
            nullData
        }
    }
}