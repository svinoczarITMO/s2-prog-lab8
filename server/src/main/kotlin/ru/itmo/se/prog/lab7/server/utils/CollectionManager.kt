package ru.itmo.se.prog.lab7.server.utils

import kotlinx.serialization.Serializable
import ru.itmo.se.prog.lab7.common.data.Person
import java.text.SimpleDateFormat
import java.util.*

/**
 * Manages the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */

class CollectionManager{
    @Serializable
    var collection: MutableCollection<Person> = Vector<Person>()

    private var supportedCollectionTypes: HashMap<String, MutableCollection<Person>> = hashMapOf()

    init {
        addSupportedCollectionType("vector", Vector())
        addSupportedCollectionType("linkedlist", LinkedList())
    }

    /**
     * Changes type of the collection.
     *
     * @param newType - new type of the collection.
     */
    fun changeType(newType: String) {
        if (collection == getSupportedCollectionTypes()[newType]!!) return
        val old = collection
        collection = supportedCollectionTypes[newType.lowercase()]!!
        for (element in old) {
            collection.add(element)
        }
    }

    /**
     * Adds new supported type of the collection in list.
     *
     * @param name - name of new supported type of the collection.
     */
    private fun addSupportedCollectionType(name: String, collection: MutableCollection<Person>) {
        supportedCollectionTypes[name] = collection
    }

    /**
     * Returns supported types of the collection.
     *
     * @return supportedCollectionTypes
     */
    fun getSupportedCollectionTypes() = supportedCollectionTypes

    /**
     * Returns type of the collection.
     *
     * @return type of the collection
     */
    fun getType(): String {
        val type = collection.javaClass.simpleName
        return type
        //Работает некорректно с пустой коллекций (Выводит "Тип: ArrayList")
    }

    /**
     * Converts collection to list.
     *
     * @return collection as List
     */
    fun collectionToList(): List<Person> {
        return collection.toList()
    }

    /**
     * Converts the date from string format to date format.
     *
     * @param dateString date in String format.
     */
    fun parseDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        return dateFormat.parse(dateString)
    }
}