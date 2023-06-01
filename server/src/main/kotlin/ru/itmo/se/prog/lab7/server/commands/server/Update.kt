package ru.itmo.se.prog.lab7.server.commands.server


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.AddPersonFields
import ru.itmo.se.prog.lab7.server.utils.io.PrinterManager

/**
 * Updates the collection item with the entered identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Update: Command(ArgType.OBJECT_PLUS, StatusType.USER, LocationType.SERVER) {
    private val set = AddPersonFields()
    private val writeToConsole = PrinterManager()
    override fun getName(): String {
        return "update"
    }

    override fun getDescription(): String {
        return " - обновляет элемент коллекции по указанному id\n"
    }

    override fun execute(data: Data): Data {
        var result: String? = ""
        val id = data.oneArg.toInt()
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
        collectionManager.collection = bufferCollection
        result = message.getMessage("updated")
        data.answerStr = result
        return data
    }
}