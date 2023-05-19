package ru.itmo.se.prog.lab7.server.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Color
import ru.itmo.se.prog.lab7.common.data.Country
import ru.itmo.se.prog.lab7.server.ServerApp
import java.io.File
import java.sql.*
import java.sql.Date


class DataBaseManager: KoinComponent {
    val user = "postgres"
    private val password = File(".psw").readText()
    private val url = "jdbc:postgresql://localhost:5433/studs"
    val collectionManager: CollectionManager by inject ()
    val serverApp: ServerApp by inject ()
    private val write: PrinterManager by inject()
    private val connectionBD = connect()

    // person table queries
    private val insertPersonQuery = connectionBD.prepareStatement(
        buildString {
        append("insert into person ")
        append("(id, name, coordinate_x , coordinate_y, creation_date, height, weight, hair_color, nationality, location_x,location_y, location_z, owner)")
        append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
    })
    private val selectPersonQuery = connectionBD.prepareStatement(buildString {
        append("select * from person;")
    })
    private val deletePersonQuery = connectionBD.prepareStatement(buildString {
        append("delete from person where person.id = ?;")
    })
    private val clearPersonQuery = connectionBD.prepareStatement(buildString {
        append("delete from person;")
    })
    // users table queries
    private val insertUsersQuery = connectionBD.prepareStatement(buildString {
        append("insert into users ")
        append("(login, password, is_admin)")
        append("values (?, ?, ?)")
    })
    private val selectUsersQuery = connectionBD.prepareStatement(buildString {
        append("select * from users")
    })
    private val clearUsersQuery = connectionBD.prepareStatement(buildString {
        append("delete from users")
    })

    fun connect(): Connection {
        try {
            val connection = DriverManager.getConnection(url, user, password)
            println("Successful connection")
            return connection
        } catch (e: SQLException) {
            throw e
        }
    }

    fun insertPerson (id: Int, name: String, coordinateX: Float, coordinateY: Float, creationDate: Date,
                      height: Int, weight: Long, hairColor: Color, nationality: Country, locationX: Int,
                      locationY: Long, locationZ: Int, owner: String) {
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
            insertPersonQuery.setString(13, owner)

            val result = insertPersonQuery.executeUpdate()
            write.linesInConsole(result)
            if (result == 0) {
                throw SQLException()
            }
        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong insert-person query")
        }
    }

    fun deletePerson (id: Int) {
        try {
            deletePersonQuery.setInt(1, id)
            deletePersonQuery.executeUpdate()
        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong delete-person query")
        }
    }

    fun clearPerson () {
        try {
            clearPersonQuery.executeUpdate()
        } catch (e: SQLException) {
            write.linesInConsole(e.message)
            write.linesInConsole("Wrong clear-person query")
        }
    }

    fun updatePerson (id: Int, name: String, coordinateX: Float, coordinateY: Float, creationDate: Date,
                      height: Int, weight: Long, hairColor: Color, nationality: Country, locationX: Int,
                      locationY: Long, locationZ: Int, owner: String) {
        deletePerson(id)
        insertPerson(id, name, coordinateX, coordinateY, creationDate, height, weight, hairColor, nationality, locationX, locationY, locationZ, owner)
    }
}