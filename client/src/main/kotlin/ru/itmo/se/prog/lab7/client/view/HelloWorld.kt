package ru.itmo.se.prog.lab7.client.view

import tornadofx.*


class HelloWorld : View() {
    override val root = hbox {
        label("Hello world")
    }
}