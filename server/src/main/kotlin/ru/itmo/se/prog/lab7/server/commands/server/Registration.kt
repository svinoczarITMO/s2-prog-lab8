package ru.itmo.se.prog.lab7.server.commands.server

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.SignManager
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager
import ru.itmo.se.prog.lab7.server.utils.Hash

class Registration: Command(ArgType.TOKEN, StatusType.USER, LocationType.SERVER), KoinComponent {
    private val signManager = SignManager()
    private val dbmanager: DataBaseManager by inject()
    private val hash = Hash()

    override fun getName(): String {
        return "reg"
    }

    override fun getDescription(): String {
        return " - создает новую учетную запись."
    }

    override fun execute(data: Data): Data {
        var result = ""
        var hashLogin = ""
        val id = signManager.getID()
        val login = signManager.regLogin(hash.encryptLogin((data.user.login))) as String

        if (login == message.getMessage("invalid_login2")) {
            data.answerStr = login
            return data
        }
        val password = this.hash.encryptPassword(data.user.password)
        val isAdmin = data.user.isAdmin
        dbmanager.insertUsers(id, login, password, isAdmin)
        dbmanager.listOfUsers.clear()
        dbmanager.uploadAllUsers()
        result = message.getMessage("successful_registration")!!
        data.answerStr = result
        return data
    }
}