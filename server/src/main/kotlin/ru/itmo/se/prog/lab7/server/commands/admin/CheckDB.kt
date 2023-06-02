package ru.itmo.se.prog.lab7.server.commands.admin

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.utils.DataBaseManager


class CheckDB: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER), KoinComponent {
    val dbmanager: DataBaseManager by inject()
    override fun getName(): String {
        return "check_db"
    }

    override fun getDescription(): String {
        return " 4ekaet\n"
    }

    override fun execute(data: Data): Data {
        println(dbmanager.listOfUsers)
        println(collectionManager.collection)
        return data
    }
}