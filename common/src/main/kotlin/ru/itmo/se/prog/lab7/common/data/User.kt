package ru.itmo.se.prog.lab7.common.data

import kotlinx.serialization.Serializable

@Serializable
data class User (
    var id: Int,
    var login: String,
    var password: String,
    val isAdmin: Boolean = false
)