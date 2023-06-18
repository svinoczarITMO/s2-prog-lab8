package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import ru.itmo.se.prog.lab7.client.app.MyApp
import ru.itmo.se.prog.lab7.client.commands.Command
import tornadofx.*

class GroupCountingByNationalityView: View() {
    private val resultText= SimpleStringProperty("")

    override val root= vbox {
        setPrefSize(350.0, 125.0)
        val result = MyApp.executeServerCommand.run("group_counting_by_nationality", mutableMapOf())
        resultText.set(result)
        label(resultText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(10.px, 10.px)
            fontSize = 17.px
            textFill = c("#000000")
            fontFamily = "Arial"
        }
    }
}