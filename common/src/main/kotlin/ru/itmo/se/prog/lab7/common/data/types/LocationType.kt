package ru.itmo.se.prog.lab7.common.data.types

import kotlinx.serialization.Serializable


@Serializable
enum class LocationType {
    CLIENT,
    SERVER
}