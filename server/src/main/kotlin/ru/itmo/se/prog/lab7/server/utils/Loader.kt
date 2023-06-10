package ru.itmo.se.prog.lab7.server.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.Serializer
import ru.itmo.se.prog.lab7.server.utils.managers.CollectionManager
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager

/**
 * Loads actual collection from Collection.xml.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Loader: KoinComponent {
    private val pathToFile = System.getenv("SERVER_COLLECTION_VAR")
    private val collectionManager: CollectionManager by inject()
    private val dbmanager: DataBaseManager by inject()
    private val serializer = Serializer()

    /**
     * Loads collection from json file.
     */
    fun load () {
        dbmanager.uploadAllPersons()
    }
}