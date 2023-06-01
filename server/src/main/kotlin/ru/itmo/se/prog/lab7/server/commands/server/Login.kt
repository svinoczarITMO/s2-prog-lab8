package ru.itmo.se.prog.lab7.server.commands.server

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.DataBaseManager


class Login: Command(ArgType.TOKEN, StatusType.USER, LocationType.SERVER), KoinComponent {
    val dbmanager: DataBaseManager by inject()

    override fun getName(): String {
        return "login"
    }

    override fun getDescription(): String {
        return " - входит в учетную запись."
    }

    override fun execute(data: Data): String? {
        val id = data.token.id
        val login = data.token.login
        val password = data.token.password
        val isAdmin = data.token.isAdmin
        dbmanager.listOfUsers.forEach {
            if (login == it.login && password == it.password) {
                return message.getMessage("successful_login")!!
            } else {
                return "ОШИБКА"
            }
        }
        return "ОШИБКА"
    }
}