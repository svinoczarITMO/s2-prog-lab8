package ru.itmo.se.prog.lab7.client.view

import ConnectDi
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.text.FontWeight
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.itmo.se.prog.lab7.client.app.MyApp
import ru.itmo.se.prog.lab7.client.utils.validation.GuiClientValidator
import tornadofx.*
import tornadofx.Stylesheet.Companion.passwordField


class RegisterView : View() {
    private var loginField: TextField by singleAssign()
    private var passwordField: PasswordField by singleAssign()
    private var repeatedPasswordField: PasswordField by singleAssign()
    private val guiClientValidator = GuiClientValidator()
    private val resultText = SimpleStringProperty("")

    override val root = vbox {
        form {
            fieldset(MyApp.bundle.getString("reg")) {
                field(MyApp.bundle.getString("username")) {
                    loginField = textfield()
                }
                field(MyApp.bundle.getString("pass")) {
                    passwordField = passwordfield()
                }
                field(MyApp.bundle.getString("conf_pass")) {
                    repeatedPasswordField = passwordfield()
                }
            }
            button(MyApp.bundle.getString("reg")) {
                action {
                    val username = loginField.text
                    val password = passwordField.text
                    val repeatedPassword = repeatedPasswordField.text
                    val commandName = "reg"
                    val flag = "main"
                    val args = mutableMapOf<String, String>("login" to username, "password" to password, "repeatedPassword" to repeatedPassword)
                    val paramList = mutableListOf(commandName, args, flag)
                    val data = guiClientValidator.validate(paramList)
                    if (data.answerStr == MyApp.di.message.getMessage("no_match_passwords")) {
                        resultText.set(MyApp.bundle.getString("no_match_passwords"))
                    } else {
                        val dataStr = Json.encodeToString(data)
                        val result = MyApp.di.clientApp.request(dataStr)
                        when (result.answerStr) {
                            MyApp.di.message.getMessage("successful_registration") -> {
                                replaceWith<DataBaseView>()
                            }

                            MyApp.di.message.getMessage("invalid_login2") -> {
                                resultText.set(MyApp.bundle.getString("invalid_login2"))
                            }
                        }
                    }
                }
            }
        }

        label(resultText).style {
            setAlignment(Pos.TOP_CENTER)
            fontSize = 14.px
            textFill = c("#de0206")
            fontFamily = "Arial"
            alignment = Pos.TOP_LEFT
        }
    }
}