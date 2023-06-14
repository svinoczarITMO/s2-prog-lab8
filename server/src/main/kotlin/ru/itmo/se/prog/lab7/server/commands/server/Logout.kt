package ru.itmo.se.prog.lab7.server.commands.server

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.ServerApp

class Logout: Command(ArgType.TOKEN, StatusType.USER, LocationType.CLIENT), KoinComponent {
    private val serverApp: ServerApp by inject()

    override fun getName(): String {
        return "logout"
    }

    override fun getDescription(): String {
        return " - выходит в учетную запись."
    }

    override fun execute(data: Data): Data {
        val result = message.getMessage("goodbye")
        data.answerStr = result
        serverApp.tokens.remove(data.token)
        return data
    }
}