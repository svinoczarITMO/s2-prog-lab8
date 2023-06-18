package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import ru.itmo.se.prog.lab7.client.app.MyApp
import tornadofx.*

class RemoveByIdView: View() {
    private val input = SimpleStringProperty("")

    override val root= form{
        fieldset {
            alignment = Pos.TOP_CENTER
            field("Enter id of element") {
                textfield(input).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Arial"
                    fontSize = 15.px
                }
            }
        }
        hbox(50, Pos.BOTTOM_RIGHT){
            button("Execute") {
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    MyApp.executeServerCommand.run("remove_by_id", mutableMapOf("oneArg" to input.value))
                    input.value=""
                }
            }
        }
    }
}