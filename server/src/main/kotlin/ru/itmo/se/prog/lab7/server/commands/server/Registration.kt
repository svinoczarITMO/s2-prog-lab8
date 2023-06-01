package ru.itmo.se.prog.lab7.server.commands.server

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.DataBaseManager

class Registration: Command(ArgType.TOKEN, StatusType.USER, LocationType.SERVER), KoinComponent {
    val dbmanager: DataBaseManager by inject()

    override fun getName(): String {
        return "reg"
    }

    override fun getDescription(): String {
        return " - создает новую учетную запись."
    }

    override fun execute(data: Data): Data {
        var result = ""
        val id = data.user.id
        val login = data.user.login
        val password = data.user.password
        val isAdmin = data.user.isAdmin
        dbmanager.insertUsers(id, login, password, isAdmin)
        result = message.getMessage("successful_registration")!!
        data.answerStr = result
        return data
    }
}