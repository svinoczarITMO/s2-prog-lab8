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

    override fun execute(data: Data): String {
//        for (i in dbmanager.listOfUsers) {
//            println(i) }
//        println(dbmanager.listOfUsers)
        val id = data.token.id
        val login = data.token.login
        val password = data.token.password
        val isAdmin = data.token.isAdmin
        dbmanager.insertUsers(id, login, password, isAdmin)
        println("login: $login \npassword: $password")

        return message.getMessage("successful_registration")!!
    }
}