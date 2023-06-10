package ru.itmo.se.prog.lab7.server.commands.server


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.AddPersonFields
import ru.itmo.se.prog.lab7.server.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager

/**
 * Updates the collection item with the entered identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Update: Command(ArgType.OBJECT_PLUS, StatusType.USER, LocationType.SERVER), KoinComponent {
    private val set = AddPersonFields()
    private val writeToConsole = PrinterManager()
    private val dbmanager: DataBaseManager by inject()
    override fun getName(): String {
        return "update"
    }

    override fun getDescription(): String {
        return " - обновляет элемент коллекции по указанному id\n"
    }

    override fun execute(data: Data): Data {
        var result: String? = ""
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
            val element = data.obj
            element.id = id
            val bufferCollection = mutableListOf<Person>()
            for (el in collectionManager.collection) {
                if (el.id != element.id) {
                    bufferCollection.add(el)
                } else {
                    bufferCollection.add(element)
                }
            }
            dbmanager.updatePerson(
                element.id, element.name, element.coordinates.x, element.coordinates.y, element.creationDate,
                element.height, element.weight, element.hairColor, element.nationality,
                element.location.x, element.location.y!!, element.location.z, data.user.id
            )
            collectionManager.collection = bufferCollection
            result = message.getMessage("updated")
            data.answerStr = result
        } else {
            result = message.getMessage("not_owner")
            data.answerStr = result
        }
        return data
    }
}