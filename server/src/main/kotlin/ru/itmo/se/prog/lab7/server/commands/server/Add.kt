package ru.itmo.se.prog.lab7.server.commands.server

import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.AddPersonFields

/**
 * Adds new element in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Add: Command(ArgType.OBJECT, StatusType.USER, LocationType.SERVER) {
    private val set = AddPersonFields()

    override fun getName(): String {
        return "add"
    }

    override fun getDescription(): String {
        return " - добавляет новый элемент в коллекцию\n"
    }

    override fun execute(data: Data): String? {
        val element = data.obj
        element.id = if (collectionManager.collection.isNotEmpty()) collectionManager.collection.maxOf { it.id } + 1 else 1
        collectionManager.collection.add(element)
        val result = (message.getMessage("added"))
        return result
    }
}
