package ru.itmo.se.prog.lab7.client.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.client.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.client.utils.io.ReaderManager
import ru.itmo.se.prog.lab7.client.utils.managers.DataBaseManager
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.exceptions.RegisterLoginException
import ru.itmo.se.prog.lab7.common.exceptions.WrongPasswordException

class AddTokenFields: KoinComponent {
    private val message: Messages by inject()
    private val write: PrinterManager by inject()
    private val read: ReaderManager by inject()
    private val dbmanager: DataBaseManager by inject()

    fun login(): Any {
        var login = ""
        dbmanager.connect()
        dbmanager.uploadAllUsers()
        try {
            write.linesInConsole(message.getMessage("enter_login"))
            val checkLogin = read.fromConsole()
            if (dbmanager.listOfUsers.isNotEmpty()) {
                dbmanager.listOfUsers.forEach {
                    if (it.login == checkLogin) {
                        throw RegisterLoginException()
                    } else {
                        login = checkLogin
                    }
                }
            } else {
                login = checkLogin
            }
        } catch (e: RegisterLoginException) {
            write.linesInConsole(message.getMessage("invalid_login2"))
            return login()
        }
        return login
    }

    fun password(): Any {
        dbmanager.connect()
        dbmanager.uploadAllUsers()
        var password = ""
        try {
            write.linesInConsole(message.getMessage("enter_password"))
            val checkPassword = read.fromConsole()
            write.linesInConsole(message.getMessage("repeat_password"))
            val repeatPassword = read.fromConsole()
            if (checkPassword != repeatPassword) {
                throw WrongPasswordException()
            } else {
                password = checkPassword
            }
        } catch (e: WrongPasswordException) {
            write.linesInConsole(message.getMessage("no_match_password"))
            return password()
        }
        return password
    }
}