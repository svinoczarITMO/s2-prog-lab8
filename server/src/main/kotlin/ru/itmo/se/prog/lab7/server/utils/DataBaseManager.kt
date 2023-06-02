package ru.itmo.se.prog.lab7.server.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.server.ServerApp
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.server.utils.io.PrinterManager
import java.io.File
import java.sql.*
import java.sql.Date


class DataBaseManager: KoinComponent {
    private val user = "postgres"
    private val password = File("D:\\ITMO\\2nd-semester\\prog-labs\\s2-prog-lab7\\server\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab7\\server\\utils\\.psw").readText()
    private val url = "jdbc:postgresql://localhost:5433/prog-lab-7"
    private val collectionManager: CollectionManager by inject ()
    private val serverApp: ServerApp by inject ()
    private val write: PrinterManager by inject()
    private val connectionBD = connect()
    val listOfUsers = mutableListOf<User>()

    // person table queries
    private val insertPersonQuery = connectionBD.prepareStatement(
        "insert into public.person " +
                "(id, name, coordinate_x , coordinate_y, creation_date, height, weight, hair_color, nationality, location_x, location_y, location_z, owner_id)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")
    private val selectPersonQuery = connectionBD.prepareStatement("select * from person;")
    private val deletePersonQuery = connectionBD.prepareStatement("delete from person where person.id = ?;")
    private val clearPersonQuery = connectionBD.prepareStatement("delete from person;")

    // users table queries
    private val insertUsersQuery = connectionBD.prepareStatement(
        "insert into public.users " +
        "(id, login, password, is_admin)" +
        "values (?, ?, ?, ?);")
    private val selectUsersQuery = connectionBD.prepareStatement("select * from users")
    private val clearUsersQuery = connectionBD.prepareStatement("delete from users")

    fun connect(): Connection {
        try {
            val connection = DriverManager.getConnection(url, user, password)
            return connection
        } catch (e: SQLException) {
            throw e
        }
    }

    fun insertPerson (id: Int, name: String, coordinateX: Float, coordinateY: Float, creationDate: Date,
                      height: Int, weight: Long, hairColor: Color, nationality: Country, locationX: Int,
                      locationY: Long, locationZ: Int, owner_id: Int) {
        connect()
        try {
            val sqlDate = Date(creationDate.time)
            val sqlHairColor = hairColor.toString().lowercase()
            val sqlNationality = nationality.toString().lowercase()
            insertPersonQuery.setInt(1, id)
            insertPersonQuery.setString(2, name)
            insertPersonQuery.setFloat(3, coordinateX)
            insertPersonQuery.setFloat(4, coordinateY)
            insertPersonQuery.setDate(5, sqlDate)
            insertPersonQuery.setInt(6, height)
            insertPersonQuery.setLong(7, weight)
            insertPersonQuery.setString(8, sqlHairColor)
            insertPersonQuery.setString(9, sqlNationality)
            insertPersonQuery.setInt(10, locationX)
            insertPersonQuery.setLong(11, locationY)
            insertPersonQuery.setInt(12, locationZ)
            insertPersonQuery.setInt(13, owner_id)

            val result = insertPersonQuery.executeUpdate()
            if (result == 0) {
                throw SQLException()
            }
        } catch (e: SQLException) {
            connect().close()
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong insert-person query")
        }
        connect().close()
    }

    fun deletePerson (id: Int) {
        connect()
        try {
            deletePersonQuery.setInt(1, id)
            deletePersonQuery.executeUpdate()
        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong delete-person query")
            connect().close()
        }
        connect().close()
    }

    fun clearPerson () {
        connect()
        try {
            clearPersonQuery.executeUpdate()
        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong clear-person query")
            connect().close()
        }
        connect().close()
    }

    fun updatePerson (id: Int, name: String, coordinateX: Float, coordinateY: Float, creationDate: Date,
                      height: Int, weight: Long, hairColor: Color, nationality: Country, locationX: Int,
                      locationY: Long, locationZ: Int, owner_id: Int) {
        connect()
        deletePerson(id)
        insertPerson(id, name, coordinateX, coordinateY, creationDate, height, weight, hairColor, nationality, locationX, locationY, locationZ, owner_id)
        connect().close()
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

    fun insertUsers (id: Int, login: String, password: String, isAdmin: Boolean) {
        connect()
        try {
            insertUsersQuery.setInt(1, id)
            insertUsersQuery.setString(2, login)
            insertUsersQuery.setString(3, password)
            insertUsersQuery.setBoolean(4, isAdmin)

            val result = insertUsersQuery.executeUpdate()
            if (result == 0) {
                throw SQLException()
            }
        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong insert-users query")
            connect().close()
        }
        connect().close()
    }

    fun clearUsers () {
        connect()
        try {
            clearUsersQuery.executeUpdate()
        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong clear-users query")
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