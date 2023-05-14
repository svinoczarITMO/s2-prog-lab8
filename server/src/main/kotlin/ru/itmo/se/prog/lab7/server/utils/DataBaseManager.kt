package ru.itmo.se.prog.lab7.server.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.server.ServerApp
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DataBaseManager: KoinComponent {
    val user = "postgres"
    val password = File(".psw").readText()
    val url = ""
    val collectionManager: CollectionManager by inject ()
    val serverApp: ServerApp by inject ()
    val connectionBD = connect()

    // person table queries
    val insertPersonQuery = connectionBD.prepareStatement(
        "insert into person " +
        "(name, coordinte_x, coordinate_y, creation_date, height, weight, hair_color, nationality, location_x,location_y, location_z, owner)" +
        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
    val selectPersonQuery = connectionBD.prepareStatement("select * from person;")
    val deletePersonQuery = connectionBD.prepareStatement("delete from person where person.id = ?;")
    val clearPersonQuery = connectionBD.prepareStatement("delete from person;")
    // users table queries
    val insertUsersQuery = connectionBD.prepareStatement("insert into users " + "(login, password, is_admin)" + "values (?, ?, ?)")
    val selectUsersQuery = connectionBD.prepareStatement("select * from users")
    val clearUsersQuery = connectionBD.prepareStatement("delete from users")

    fun connect(): Connection {
        try {
            val connection = DriverManager.getConnection(url, user, password)
            println("Successful connection")
            return connection
        } catch (e: SQLException) {
            throw e
        }
    }
}