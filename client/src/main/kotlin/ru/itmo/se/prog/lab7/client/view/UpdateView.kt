package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import ru.itmo.se.prog.lab7.client.app.MyApp
import ru.itmo.se.prog.lab7.common.data.Color
import ru.itmo.se.prog.lab7.common.data.Country
import tornadofx.*

class UpdateView: View() {
    private var inputId = SimpleStringProperty()
    private var inputName = SimpleStringProperty()
    private var inputCoordinateX = SimpleStringProperty()
    private var inputCoordinateY = SimpleStringProperty()
    private var inputHeight = SimpleStringProperty()
    private var inputWeight = SimpleStringProperty()
    private var inputHairColor = SimpleStringProperty()
    private var inputCountry = SimpleStringProperty()
    private var inputLocationX = SimpleStringProperty()
    private var inputLocationY = SimpleStringProperty()
    private var inputLocationZ = SimpleStringProperty()
    private var resultText = SimpleStringProperty()


    override val root = form {
        setPrefSize(1000.0, 450.0)
        fieldset {
            field(MyApp.bundle.getString("update_id")) {
                textfield(inputId).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("name")) {
                textfield(inputName).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("coordinate_x")) {
                textfield(inputCoordinateX).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("coordinate_y")) {
                textfield(inputCoordinateY).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("height")) {
                textfield(inputHeight).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("weight")) {
                textfield(inputWeight).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field("${MyApp.bundle.getString("hair_color")}: ${Color.values().map { it.toString() }}") {
                textfield(inputHairColor).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field("${MyApp.bundle.getString("nationality")}: ${Country.values().map { it.toString() }}") {
                textfield(inputCountry).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("location_x")) {
                textfield(inputLocationX).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("location_y")) {
                textfield(inputLocationY).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("location_z")) {
                textfield(inputLocationZ).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
        }.style {
            setMaxSize(750.0, 900.0)
            alignment = Pos.TOP_CENTER
        }
        hbox(50, Pos.BOTTOM_RIGHT) {
            button("Execute") {
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = javafx.scene.paint.Color.WHITE
                    backgroundColor += javafx.scene.paint.Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    try {
                        MyApp.guiClientValidator.params = arrayListOf(
                            inputName.value,
                            inputCoordinateX.value,
                            inputCoordinateY.value,
                            inputHeight.value,
                            inputWeight.value,
                            inputHairColor.value,
                            inputCountry.value,
                            inputLocationX.value,
                            inputLocationY.value,
                            inputLocationZ.value
                        )
                        when (MyApp.executeServerCommand.run("update", mutableMapOf("oneArg" to inputId.value), "gui")) {
                            MyApp.di.message.getMessage("updated") -> {
                                resultText.set(MyApp.bundle.getString("updated"))
                            }
                            MyApp.di.message.getMessage("not_owner") -> {
                                resultText.set(MyApp.bundle.getString("not_owner"))
                            }
                        }

                    } catch (e: Exception) {
                        resultText.set("Something wrong in update view")
                    }

                    inputName.value = ""
                    inputCoordinateX.value = ""
                    inputCoordinateY.value = ""
                    inputHeight.value = ""
                    inputWeight.value = ""
                    inputHairColor.value = ""
                    inputCountry.value = ""
                    inputLocationX.value = ""
                    inputLocationY.value = ""
                    inputLocationZ.value = ""
                }
            }
            label(resultText).style {
                setAlignment(Pos.TOP_CENTER)
                padding = box(30.px, 20.px)
            }
        }
    }
}
