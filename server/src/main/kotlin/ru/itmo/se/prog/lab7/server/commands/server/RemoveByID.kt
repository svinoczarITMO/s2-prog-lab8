package ru.itmo.se.prog.lab7.server.commands.server


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager

/**
 * Deletes the collection item with the entered identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class RemoveByID: Command(ArgType.ONE_ARG, StatusType.USER, LocationType.SERVER), KoinComponent {
    private val dbmanager: DataBaseManager by inject()
    override fun getName(): String {
        return "remove_by_id"
    }

    override fun getDescription(): String {
        return " ${Messages.cyanText}--id${Messages.resetColor} - удаляет элемент из коллекции по его id\n"
    }

    override fun execute(data: Data): Data {
        var result: String? = ""
        var flag = false
        val id = data.oneArg.toInt()
        var ownerId = 0
        val person = dbmanager.selectPersonQuery.executeQuery()
        while (person.next()) {
            if (id == person.getInt("id")) {
                ownerId = person.getInt("owner_id")
                break
            }
        }
        if (data.user.id == ownerId) {
            try {
                try {
                    for (element in collectionManager.collection) {
                        if (element.id == id) {
                            collectionManager.collection.remove(element)
                            dbmanager.deletePerson(id)
                            flag = true
                            break
                        }
                    }
                } catch (e: ArrayIndexOutOfBoundsException) {
                    result = e.message
                    data.answerStr = result
                    return data
                }

                if (flag) {
                    result = (
                            message.getMessage("by_id") +
                                    id +
                                    message.getMessage("removed")
                            )
                } else if (!flag) {
                    result = (message.getMessage("invalid_id"))
                }
            } catch (e: ArrayIndexOutOfBoundsException) {
                result = (message.getMessage("InvalidArgument"))
                data.answerStr = result
                return data
            }
        } else {
            result = message.getMessage("not_owner")
            data.answerStr = result
        }
        data.answerStr = result
        return data
    }
}