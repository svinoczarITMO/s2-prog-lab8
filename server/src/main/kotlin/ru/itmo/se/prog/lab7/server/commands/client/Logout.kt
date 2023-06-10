package ru.itmo.se.prog.lab7.server.commands.client

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.User
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.commands.Command
import kotlin.system.exitProcess

class Logout: Command(ArgType.NO_ARG, StatusType.USER, LocationType.CLIENT), KoinComponent {


    override fun getName(): String {
        return "logout"
    }

    override fun getDescription(): String {
        return " - завершает текущую сессию (выходит из аккаунта)\n"
    }

    override fun execute(data: Data): Data {
        var result: String? = ""
        result += message.getMessage("log_out") + message.getMessage("goodbye")
        data.answerStr = result
        return data
    }
}