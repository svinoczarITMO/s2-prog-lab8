package ru.itmo.se.prog.lab7.client.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.client.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.client.utils.io.ReaderManager
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.exceptions.RegisterLoginException
import ru.itmo.se.prog.lab7.common.exceptions.SignInLoginException
import ru.itmo.se.prog.lab7.common.exceptions.WrongPasswordException

class AddTokenFields: KoinComponent {
    private val message: Messages by inject()
    private val write: PrinterManager by inject()
    private val read: ReaderManager by inject()
    private val dbmanager: DataBaseManager by inject()
    private var wrongPasswordCounter = 0

    fun getID(): Int {
        if (dbmanager.listOfUsers.isEmpty()) {
            return 1
        } else {
            return dbmanager.listOfUsers.last().id + 1
        }
    }

    fun regLogin(): Any {
        var login = ""
        try {
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
            return regLogin()
        }
        return login
    }

    fun regPassword(): Any {
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
            return regPassword()
        }
        return password
    }

    fun logLogin(): Any {
        var login = ""
        try {
            val checkLogin = read.fromConsole()
            if (dbmanager.listOfUsers.isEmpty()) {
                throw SignInLoginException()
            }
            dbmanager.listOfUsers.forEach {
                if (it.login == checkLogin) {
                    login = checkLogin
                }
            }
            if (login == "") {
                throw SignInLoginException()
            }
            } catch (e: SignInLoginException) {
                write.linesInConsole(message.getMessage("invalid_login1"))
                return logLogin()
            }
            return login
        }

    fun logPassword(): Any {
        var password = ""
        try {
            val checkPassword = read.fromConsole()
            dbmanager.listOfUsers.forEach {
                if (it.password == checkPassword) {
                    password = checkPassword
                }
            }
            if (password == "") {
                throw WrongPasswordException()
            }
        } catch (e: WrongPasswordException) {
            wrongPasswordCounter += 1
            if (wrongPasswordCounter < 3) {
                write.linesInConsole(message.getMessage("invalid_password"))
                return logPassword()
            } else {
                write.linesInConsole(message.getMessage("attempts_are_over"))
            }
        }
        wrongPasswordCounter = 0
        return password
    }
}