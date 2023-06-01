package ru.itmo.se.prog.lab7.server.commands.server

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.common.exceptions.RegisterLoginException
import ru.itmo.se.prog.lab7.common.exceptions.WrongPasswordException
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
//        dbmanager.connect()
        dbmanager.uploadAllUsers()
        for (i in dbmanager.listOfUsers) {
            println(i) }
        println(dbmanager.listOfUsers)
        val login = data.token.login
        val password = data.token.password
        println("login: $login \npassword: $password")
        dbmanager.insertUsers(login, password, false)
        return message.getMessage("successful_registration")!!
    }
}