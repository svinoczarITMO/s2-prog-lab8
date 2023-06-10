package ru.itmo.se.prog.lab7.server.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.exceptions.RegisterLoginException
import ru.itmo.se.prog.lab7.common.exceptions.SignInLoginException
import ru.itmo.se.prog.lab7.common.exceptions.WrongPasswordException
import ru.itmo.se.prog.lab7.server.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager

class SignManager: KoinComponent {
    private val message: Messages by inject()
    private val write: PrinterManager by inject()
    private val dbmanager: DataBaseManager by inject()
    private var wrongPasswordCounter = 0

    fun getID(): Int {
        return if (dbmanager.listOfUsers.isEmpty()) {
            1
        } else {
            dbmanager.listOfUsers.last().id + 1
        }
    }

    fun regLogin(checkLogin: String): Any {
        var login = ""
        try {
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
            login = message.getMessage("invalid_login2")!!
            return login
        }
        return login
    }

    fun regPassword(login:String, checkPassword: String): Any {
        var password = ""
        try {
            val pswFromDB = dbmanager
                .connect()
                .prepareStatement("select password from users where users.login = '$login'")
                .executeQuery()

            while (pswFromDB.next()) { password = pswFromDB.getString("password") }
            if (checkPassword == password) {
                password = checkPassword
            }
        } catch (e: WrongPasswordException) {
            write.linesInConsole(message.getMessage("wrong_registration"))
            password = message.getMessage("wrong_registration")!!
            return password
        }
        return password
    }

    fun logLogin(checkLogin: String): Any {
        var login = ""
        try {
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
                login = message.getMessage("invalid_login1")!!
                return login
            }
            return login
        }

    fun logPassword(checkPassword: String): Any {
        var password = ""
        try {
            dbmanager.listOfUsers.forEach {
                if (it.password == checkPassword) {
                    password = checkPassword
                }
            }
            if (password == "") {
                throw WrongPasswordException()
            }
        } catch (e: WrongPasswordException) {
                write.linesInConsole(message.getMessage("invalid_password"))
                password = message.getMessage("invalid_password")!!
                return password
        }
        wrongPasswordCounter = 0
        return password
    }
}