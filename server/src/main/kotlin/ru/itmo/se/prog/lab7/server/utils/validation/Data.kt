package ru.itmo.se.prog.lab7.server.utils.validation

import kotlinx.serialization.Serializable
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType

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
