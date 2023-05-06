package ru.itmo.se.prog.lab7.server.commands


import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.utils.Logger
import ru.itmo.se.prog.lab7.server.utils.validation.Data

/**
 * Outputs information about the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Info: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    private val logger = Logger()
    override fun getName(): String {
        return "info"
    }

    override fun getDescription(): String {
        return getName() + " - выводит  в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)\n"
    }

    override fun execute(data: Data): String? {
        var result: String? = ""
        val type = collectionManager.getType() //Работает некорректно с пустой коллекций (Выводит "Тип: ArrayList")
        val size = collectionManager.collection.size
        val initDate = logger.initDate(collectionManager)
        result = (
                    "Тип: " + type + "\n"
                    + "Размер: " + size + "\n"
                    + "Дата инициализации: " + initDate
                )
        return result
    }
}
