package ru.itmo.se.prog.lab7.client.view

import javafx.geometry.Pos
import ru.itmo.se.prog.lab7.client.app.MyApp
import tornadofx.*
import java.util.*


class MainView : View() {
    override val root = hbox {
        button("Sign in") {
            action {
                replaceWith<LoginView>()
            }
            prefWidth = 150.0
            prefHeight = 85.0
            style {
                fontSize = 14.px
                backgroundColor += c("#3b7bc4")
                backgroundRadius += box(5.px)
                textFill = c("#ffffff")
                fontFamily = "Georgia"
            }
        }

        button("Sign up") {
            action {
                replaceWith<RegisterView>()
            }
            prefWidth = 150.0
            prefHeight = 85.0
            style {
                fontSize = 14.px
                backgroundColor += c("#3b7bc4")
                backgroundRadius += box(5.px)
                textFill = c("#ffffff")
                fontFamily = "Georgia"
            }
        }

        // CSS style to center the buttons horizontally
        style {
            alignment = javafx.geometry.Pos.CENTER
            spacing = 20.px // Add some space between the buttons
        }

        hbox(10, Pos.CENTER_LEFT) {
            val toggle = togglegroup {
                togglebutton("ru") {
                    action {
                        MyApp.setBundle = ResourceBundle.getBundle("messages", Locale("ru"))

                    }
                }
                togglebutton("zh") {
                    action {
                        MyApp.setBundle = ResourceBundle.getBundle("messages", Locale("zh"))
                    }
                }
            }
        }
        onRefresh()
    }
}
