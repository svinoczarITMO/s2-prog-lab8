package ru.itmo.se.prog.lab7.common.data

import kotlinx.serialization.Serializable

@Serializable
data class Token (
    val login: String,
    val password: String
)