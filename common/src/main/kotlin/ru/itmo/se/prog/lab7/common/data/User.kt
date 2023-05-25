package ru.itmo.se.prog.lab7.common.data

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val isAdmin: Boolean
)
