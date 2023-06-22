package ru.itmo.se.prog.lab7.client.commands.client

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.client.ClientApp
import ru.itmo.se.prog.lab7.client.commands.Command
import ru.itmo.se.prog.lab7.client.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.User
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import kotlin.system.exitProcess

class Logout: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER), KoinComponent {
    private val clientApp: ClientApp by inject()

    override fun getName(): String {
        return "logout"
    }

    override fun getDescription(): String {
        return " - завершает текущую сессию (выходит из аккаунта)\n"
    }

    override fun execute(data: Data): String? {
        var result: String? = "."
        clientApp.token = ""
        clientApp.user = User(-999, "", "", false)
        clientApp.authorized = false
        result += message.getMessage("log_out")
        return result
    }
}