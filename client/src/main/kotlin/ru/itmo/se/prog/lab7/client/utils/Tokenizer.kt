package ru.itmo.se.prog.lab7.client.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.client.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.client.utils.io.ReaderManager
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.exceptions.ElementAmountException

class Tokenizer: KoinComponent {
    private val message: Messages by inject()
    private val write: PrinterManager by inject()
    private val read: ReaderManager by inject()

    fun tokenizeLogin (arg: String, flag: String): String {
        val login: String = if (flag == "main") {
            write.linesInConsole(message.getMessage("enter_login"))
            read.fromConsole()
        } else {
            arg
        }
        try {
            if (login.isBlank()) throw ElementAmountException()
            return login
        } catch (e: ElementAmountException) {
            write.linesInConsole("")
        }
        return tokenizeLogin(login, flag)
    }
}