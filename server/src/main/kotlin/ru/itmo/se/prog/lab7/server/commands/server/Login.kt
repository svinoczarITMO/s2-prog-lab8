package ru.itmo.se.prog.lab7.server.commands.server

import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command


class Login: Command(ArgType.TOKEN, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "login"
    }

    override fun getDescription(): String {
        return " - входит в учетную запись."
    }

    override fun execute(data: Data): String? {
        return "// will not implement"
    }
}