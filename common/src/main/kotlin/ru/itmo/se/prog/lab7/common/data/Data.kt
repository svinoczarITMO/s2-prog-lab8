package ru.itmo.se.prog.lab7.common.data

import kotlinx.serialization.Serializable
import ru.itmo.se.prog.lab7.common.data.types.*

@Serializable
data class Data(
    var name: String,
    var oneArg: String,
    var obj: Person,
    var token: String,
    var user: User,
    var placeFlag: String,
    var argType: ArgType,
    var statusType: StatusType,
    var locationType: LocationType,
    var answerStr: String? = "not null"
)