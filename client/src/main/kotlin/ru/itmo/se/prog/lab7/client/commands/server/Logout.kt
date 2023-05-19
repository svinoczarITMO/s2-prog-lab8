package ru.itmo.se.prog.lab7.client.commands.server

import ru.itmo.se.prog.lab7.client.commands.Command
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType

class Logout: Command(ArgType.TOKEN, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getDescription(): String {
        TODO("Not yet implemented")
    }

    override fun execute(data: Data): String? {
        TODO("Not yet implemented")
    }
}