package ru.itmo.se.prog.lab7.server.commands.admin


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager

/**
 * Adds new element to the collection with no input arguments.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class FastAdd: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER), KoinComponent {
    private val dbmanager: DataBaseManager by inject()

    override fun getName(): String {
        return "fadd"
    }

    override fun getDescription(): String {
        return " - добавляет новый элемент в коллекцию без указания параметров элемента\n"
    }

    override fun execute(data: Data): Data {
        val id: Int = if (collectionManager.collection.isNotEmpty()) collectionManager.collection.maxOf { it.id } + 1000 else 1000
        val obj = ru.itmo.se.prog.lab7.common.data.Person(
            id,
            "Jesus",
            Coordinates(0f, 0f),
            collectionManager.parseDate("Fri Jul 19 12:00:00 MSK 2023"),
            305,
            0,
            Color.RED,
            Country.USA,
            Location(0, 0, 0),
            -1
        )
        dbmanager.insertPerson(obj, data.user.id)
        collectionManager.collection.add(obj)
        data.answerStr = message.getMessage("added")
        return data
    }
}