package ru.itmo.se.prog.lab7.client.commands.server

import ru.itmo.se.prog.lab7.client.commands.Command
import ru.itmo.se.prog.lab7.client.utils.AddPersonFields
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType

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
//        val flag = data.placeFlag
//        val id: Int = if (collectionManager.collection.isNotEmpty()) collectionManager.collection.maxOf { it.id } + 1 else 1
//        var params = arrayListOf("null parameter", "null parameter", "null parameter", "null parameter", "null parameter",
//                                         "null parameter", "null parameter", "null parameter", "null parameter", "null parameter")
        var result: String? = ""
//

        result = (message.getMessage("added"))
        return result
    }
}
