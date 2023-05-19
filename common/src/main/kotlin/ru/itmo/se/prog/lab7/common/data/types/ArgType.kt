package ru.itmo.se.prog.lab7.common.data.types

import kotlinx.serialization.Serializable

@Serializable
enum class ArgType {
    NO_ARG,
    ONE_ARG,
    OBJECT,
    OBJECT_PLUS,
    TOKEN
}