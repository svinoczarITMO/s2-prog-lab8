package ru.itmo.se.prog.lab7.client.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.client.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.client.utils.managers.CollectionManager
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.common.data.Person
import java.io.File
import java.sql.*
import java.sql.Date


class DataBaseManager: KoinComponent {
    private val user = "postgres"
    private val password = File("D:\\ITMO\\2nd-semester\\prog-labs\\s2-prog-lab7\\server\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab7\\server\\utils\\.psw").readText()
    private val url = "jdbc:postgresql://localhost:5433/prog-lab-7"
    private val collectionManager: CollectionManager by inject ()
    private val write: PrinterManager by inject()
    private val connectionBD = connect()
    private val selectPersonQuery = connectionBD.prepareStatement("select * from person;")
    private val selectUsersQuery = connectionBD.prepareStatement("select * from users;")
    val listOfUsers = mutableListOf<User>()

    fun connect(): Connection {
        try {
            val connection = DriverManager.getConnection(url, user, password)
            return connection
        } catch (e: SQLException) {
            throw e
        }
    }

    fun uploadAllPersons () {
        connect()
        try {
            val persons = selectPersonQuery.executeQuery()
            while (persons.next()) {
                val coordinates = Coordinates(persons.getFloat("coordinate_x"), persons.getFloat("coordinate_y"))
                val location = Location(persons.getInt("location_x"), persons.getLong("location_y"), persons.getInt("location_z"))

                val personToAdd = Person(
                    persons.getInt("id"),
                    persons.getString("name"),
                    coordinates,
                    persons.getDate("creation_date"),
                    persons.getInt("height"),
                    persons.getLong("weight"),
                    Color.valueOf(persons.getString("hair_color").uppercase()),
                    Country.valueOf(persons.getString("nationality").uppercase()),
                    location
                )
                collectionManager.collection.add(personToAdd)
            }

        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong upload-all-persons query")
            connect().close()
        }
        connect().close()
    }

    fun uploadAllUsers () {
        connect()
        try {
            val users = selectUsersQuery.executeQuery()
            while (users.next()) {
                val userToAdd = User(
                    users.getInt("id"),
                    users.getString("login"),
                    users.getString("password"),
                    users.getBoolean("is_admin")
                )
                listOfUsers.add(userToAdd)
            }

        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong upload-all-users query")
            connect().close()
        }
        connect().close()
    }
}