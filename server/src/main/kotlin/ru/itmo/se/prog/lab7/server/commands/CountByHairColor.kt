package ru.itmo.se.prog.lab7.server.commands

import ru.itmo.se.prog.lab7.common.data.Color
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.utils.validation.Data

/**
 * Counts elements by hair color.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class CountByHairColor: Command(ArgType.ONE_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "count_by_hair_color"
    }

    override fun getDescription(): String {
        return getName() + " --hairColor - выводит количество элементов, значение поля hairColor которых равно заданному\n"
    }

    override fun execute(data: Data): String? {
        println(data.oneArg)
        val color = Color.valueOf(data.oneArg.uppercase())
        val copyVector = collectionManager.collection
        var counter = 0
        var result: String? = ""
        try {
            for (element in copyVector) {
                if (element.hairColor == color) {
                    counter += 1
                }
            }
            result = ("Количество людей с цветом волос \"${color.toString().capitalize()}\": $counter")
        } catch (e: IllegalArgumentException) {
            result = (message.getMessage("IllegalColor"))
        }
        return result
    }
}