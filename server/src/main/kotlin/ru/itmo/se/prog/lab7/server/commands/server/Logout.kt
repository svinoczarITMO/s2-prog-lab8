package ru.itmo.se.prog.lab7.server.commands.server

import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType

class Logout: Command(ArgType.TOKEN, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "logout"
    }

    override fun getDescription(): String {
        return " - выходит в учетную запись."
    }

    override fun execute(data: Data): String? {
        return "// will implement"
    }
}