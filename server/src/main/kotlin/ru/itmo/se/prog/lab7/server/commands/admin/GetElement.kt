package ru.itmo.se.prog.lab7.server.commands.admin


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.server.commands.Command

/**
 * Gets the collection item by its identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class GetElement: Command(ArgType.ONE_ARG, StatusType.ADMIN, LocationType.SERVER) {
    override fun getName(): String {
        return "get"
    }

    override fun getDescription(): String {
        return " - выводит в терминал объект коллекции с указанным id\n"
    }
    /**
     *
     * @param Int id of getting element.
     */
    override fun execute(data: Data): Data {
        var result: String? = ""
        var obj: Person? = null
        val id = data.oneArg.toInt()
        try {
            for (element in collectionManager.collection) {
                if (element.id == id) {
                    obj = element
                    break
                }
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            result = (message.getMessage("invalid_id"))
        }
        obj?.let {
            result = (
                "Объект ${it.id}:\n"
                        + "Дата создания: \"" + it.creationDate + "\" \n"
                        + "Координаты: x = " + it.coordinates.x + " y = " + it.coordinates.y + "\n"
                        + "Имя: " + it.name + "\n"
                        + "Рост: " + it.height + "\n"
                        + "Вес: " + it.weight + "\n"
                        + "Цвет волос: " + it.hairColor + "\n"
                        + "Национальность: " + it.nationality + "\n"
                        + "Местоположение: x = " + it.location.x + "; y = " + it.location.y + "; z = " + it.location.z
            )
        } ?:(message.getMessage("invalid_id"))
        data.answerStr = result
        return data
    }
}