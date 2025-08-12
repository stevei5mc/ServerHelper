rootProject.name = "ServerHelper"

include(":ServerHelper-WaterdogPE",":ServerHelper-Nukkit")

project(":ServerHelper-WaterdogPE").projectDir = file("WaterdogPE")
project(":ServerHelper-Nukkit").projectDir = file("Nukkit")