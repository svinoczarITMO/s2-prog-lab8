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

    override fun execute(data: Data): Data {
        var result = ""
        val id = data.user.id
        val login = data.user.login
        val password = data.user.password
        val isAdmin = data.user.isAdmin
        dbmanager.listOfUsers.forEach {
            if (login == it.login && password == it.password) {
                result = message.getMessage("successful_login")!!
                return data
            } else {
                result = "ОШИБКА"
                return data
            }
        }
        result = "ОШИБКА"
        data.answerStr = result
        return data
    }
}