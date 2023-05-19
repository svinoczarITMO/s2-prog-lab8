package ru.itmo.se.prog.lab7.server.commands.server


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command
import java.io.File

/**
 * Outputs the last 7 commands.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class History: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "history"
    }

    override fun getDescription(): String {
        return " - выводит последние 7 команд (без их аргумента)\n"
    }

    override fun execute(data: Data): String? {
        val buffer = File("D:\\ITMO\\2nd-semester\\prog-labs\\s2-prog-lab6\\server\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab6\\data\\history.log").readText().split(", ")
        var result: String? = ""
        result += "\n"
        result = (message.getMessage("last_commands"))
        for (command in buffer) {
            result += (command) + "\n"
        }
        println(result)
        return result
    }
}