package ru.itmo.se.prog.lab7.server.commands.server

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.DataBaseManager
import ru.itmo.se.prog.lab7.server.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.server.utils.io.ReaderManager

class Registration: Command(ArgType.TOKEN, StatusType.USER, LocationType.SERVER), KoinComponent {
    val dbmanager: DataBaseManager by inject()

    override fun getName(): String {
        return "reg"
    }

    override fun getDescription(): String {
        return " - создает новую учетную запись."
    }

    override fun execute(data: Data): String {
        dbmanager.connect()
        dbmanager.uploadAllUsers()
        try {
            write.linesInConsole(message.getMessage("enter_login"))
            val checkLogin = read.fromConsole()
            dbmanager.listOfUsers.forEach {
                if (it.login == checkLogin) {
                    write.linesInConsole(message.getMessage("invalid_login2"))
                }
            }
            val login = checkLogin

            write.linesInConsole(message.getMessage("enter_password"))
            val password = read.fromConsole()

            write.linesInConsole(message.getMessage("repeat_password"))
            val actPassword = read.fromConsole()
            if (password == actPassword) {
                dbmanager.insertUsers(login, password, false)
            }
        } catch (e: Exception) {

        }
    }

    fun checkLogin () {
        //TODO not implemented yet
    }
}