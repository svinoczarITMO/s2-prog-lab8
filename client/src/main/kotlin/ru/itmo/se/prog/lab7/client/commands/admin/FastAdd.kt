package ru.itmo.se.prog.lab7.client.commands.admin

import ru.itmo.se.prog.lab7.client.commands.Command
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType


/**
 * Adds new element to the collection with no input arguments.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class FastAdd: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER) {
    override fun getName(): String {
        return "fadd"
    }

    override fun getDescription(): String {
        return " - добавляет новый элемент в коллекцию без указания параметров элемента\n"
    }

    override fun execute(data: Data): String? {
        val id: Int = if (collectionManager.collection.isNotEmpty()) collectionManager.collection.maxOf { it.id } + 1000 else 1000
        val obj = Person(id, "Jesus", Coordinates(0f,0f), collectionManager.parseDate("Fri Jul 19 12:00:00 MSK 2023"), 305, 0, Color.RED, Country.USA, Location(0,0,0), -1)
        collectionManager.collection.add(obj)
        val result = message.getMessage("added")
        return result
    }
}