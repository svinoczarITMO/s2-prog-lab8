package ru.itmo.se.prog.lab7.client.utils.validation

import kotlinx.serialization.Serializable
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.common.data.Person

@Serializable
data class Data(
    var name: String,
    var oneArg: String,
    var obj: Person,
    var placeFlag: String,
    var argType: ArgType,
    var statusType: StatusType,
    var locationType: LocationType
)
