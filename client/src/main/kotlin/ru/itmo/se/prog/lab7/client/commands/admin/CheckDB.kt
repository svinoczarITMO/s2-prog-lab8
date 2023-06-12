package ru.itmo.se.prog.lab7.client.commands.admin

import ru.itmo.se.prog.lab7.client.commands.Command
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType


class CheckDB: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER) {
    override fun getName(): String {
        return "check_db"
    }

    override fun getDescription(): String {
        return " 4ekaet\n"
    }

    override fun execute(data: Data): String? {
        return ""
    }
}