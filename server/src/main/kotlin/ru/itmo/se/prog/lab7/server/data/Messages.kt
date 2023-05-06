package ru.itmo.se.prog.lab7.server.data

/**
 * Contains messages for output.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Messages {
    private val messages = mapOf(
        //reading data
        "enter_name" to "Введите имя: ",
        "enter_coordinateX" to "Введите координату X места рождения: ",
        "enter_coordinateY" to "Введите координату Y места рождения: ",
        "enter_height" to "Введите рост: ",
        "enter_weight" to "Введите вес: ",
        "enter_hairColor" to "Выберите цвет волос из предложенных: ",
        "enter_nationality" to "Выберите страну рождения из предложенных: ",
        "enter_locationX" to "Введите координату X примерного текущего местоположения: ",
        "enter_locationY" to "Введите координату Y примерного текущего местоположения: ",
        "enter_locationZ" to "Введите координату Z примерного текущего местоположения: ",

        //successes
        "added" to "Эллемент успешно добавлен.",
        "updated" to "Эллемент успешно обновлён.",
        "clear" to "Коллекция успешно очищена!",
        "removed" to " успешно удалён!",
        "saved" to "Коллекция успешно сохранена.",
        "reordered" to "Коллекция успешно отсортирована в обратном порядке.",
        "type_changed" to "Тип коллекции успешно сменен на ",

        //warnings
        "recurision" to "Превышена глубина рекурсии! Выполнение прекращено!",
        "not_enough_args" to "Не хватает параметров в скрипте для добавления новой команды!\n",
        "weird_command" to "Введена неверная команда. Используйте help для вывода списка команд.\n",
        "invalid_id" to "Объект с указанным id не найден.\n",

        //info
        "script_start" to "Скрипт выполняется...",
        "script_end" to "Выполнение скрипта завершено...",
        "last_commands" to "Последние 7 комманд: ",
        "min_weight_id" to "Id объекта коллекции с минимальным весом: ",
        "weight" to "Вес: ",
        "first_element" to "Первый элемент коллекции ",
        "by_id" to "Элемент с id = ",
        "clean_collection" to "В коллекции не содержится элементов.",
        "supported_types" to "Вы можете использовать следующие типы коллекции:\n",

        //extra
        "=" to "=========================================================================================================================================\n",

        //exceptions
        "NumberFormatException" to "Введенное значение должно быть числом! Попробуйте ещё раз.\n",
        "IndexOutOfRange" to "Введенное число выходит за диапозон допустимых значений! Попробуйте ещё раз.\n",
        "IllegalColor" to "Введенный цвет не соответствует ни одному из предложенных! Попробуйте ещё раз.\n",
        "IllegalCountry" to "Введенная страна не соответствует ни одной из предложенных! Попробуйте ещё раз.\n",
        "InvalidArgument" to "Введенно недопустимое значение аргумента.\n",
        "NoSuchFile" to "Указанный файл не найден.\n",
        "NoSuchType" to "Указанный тип коллекции не найден.\n",
        "InvalidCommand" to "Такой команды не существует. Напишите help для просмотра списка команд.\n"
    )

    /**
     * Returns message from map of messages by inputed key.
     *
     * @param key key of message
     * @return message as String?
     */
    fun getMessage (key: String): String? {
        return messages[key]
    }
}