package ru.itmo.se.prog.lab7.server.commands.server


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager

/**
 * Clears the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Clear: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER), KoinComponent {
    val dbmanager: DataBaseManager by inject()

    override fun getName(): String {
        return "clear"
    }

    override fun getDescription(): String {
        return " - очищает коллекцию\n"
    }

    override fun execute(data: Data): Data {
        val result = (message.getMessage("clear"))
        val id = data.user.id
        collectionManager.collection.forEach{
            if (id == it.ownerId) {
                dbmanager.deletePerson(it.id)
                collectionManager.collection.remove(it)
            }
        }
        data.answerStr = result
        return data
    }
}