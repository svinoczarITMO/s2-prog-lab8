package ru.itmo.se.prog.lab7.common.data

/**
 * Contains messages for output.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Messages {

    companion object {
        val redText = "\u001B[31m"
        val cyanText = "\u001B[1;36m"
        val resetColor = "\u001B[0m"
    }

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
        "enter_login" to "Введите имя пользователя (логин): ",
        "enter_password" to "Введите пароль: ",
        "repeat_password" to "Повторите пароль: ",

        //successes
        "added" to "Эллемент успешно добавлен.",
        "updated" to "Эллемент успешно обновлён.",
        "clear" to "Коллекция успешно очищена!",
        "removed" to " успешно удалён!",
        "saved" to "Коллекция успешно сохранена.",
        "reordered" to "Коллекция успешно отсортирована в обратном порядке.",
        "type_changed" to "Тип коллекции успешно сменен на ",
        "successful_registration" to "Регистрация прошла успешно! Для дальнейшей работы войдите в аккаунт. (комманда ${cyanText}login$resetColor)",
        "successful_login" to "Авторизация прошла успешно! Добро пожаловать!",

        //warnings
        "recursion" to "Скрипт содержит рекурсию! Выполнение невозможно!",
        "not_enough_args" to "Не хватает параметров в скрипте для добавления новой команды!\n",
        "weird_command" to "Введена неверная команда. Используйте help для вывода списка команд.\n",
        "invalid_id" to "Объект с указанным id не найден.\n",
        "blank_string" to "Строка не может быть пустой! Введите имя ещё раз.",
        "invalid_login1" to "Такого логина не существует.\n Попробуйте еще раз (комманда ${cyanText}login$resetColor)" +
                "или зарегистрируйтесь (команда ${cyanText}reg$resetColor).",
        "invalid_login2" to "Введенное имя пользователя уже существует.\n Придумайте другое: ",
        "invalid_password" to "Пароль неверный.\n Попробуйте ещё раз: ",
        "no_match_passwords" to "Пароль неверный.\n Попробуйте ещё раз: ",
        "access_denied" to "Недостаточно прав для выполнения данной команды.",

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
        "login_info" to "Для дальнейшей работы вам необходимо войти в свой аккаунт (комманда ${cyanText}login$resetColor)\n" +
                "или зарегистрироваться (команда ${cyanText}reg$resetColor)",
        "permission_upgrade" to "Для повышения прав данного аккаунта введите команду ${cyanText}uta --key$resetColor (где \"key\" - ключ, выдаваемый администратором).",


        //extra
        "=" to "=========================================================================================================================================",
        "welcome" to "Добро пожаловать, ",

        //exceptions
        "NumberFormatException" to "${redText}Введенное значение должно быть числом! Попробуйте ещё раз.$resetColor",
        "IndexOutOfRange" to "${redText}Введенное число выходит за диапозон допустимых значений! Попробуйте ещё раз.$resetColor",
        "IllegalColor" to "${redText}Введенный цвет не соответствует ни одному из предложенных! Попробуйте ещё раз.$resetColor",
        "IllegalCountry" to "${redText}Введенная страна не соответствует ни одной из предложенных! Попробуйте ещё раз.$resetColor",
        "InvalidArgument" to "${redText}Введенно недопустимое значение аргумента.$resetColor",
        "NoSuchFile" to "${redText}Указанный файл не найден.$resetColor",
        "NoSuchType" to "${redText}Указанный тип коллекции не найден.$resetColor",
        "InvalidCommand" to "${redText}Такой команды не существует. Напишите help для просмотра списка команд.$resetColor"
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