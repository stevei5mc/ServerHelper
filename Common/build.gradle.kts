tasks {
    processResources { enabled = false }
    processTestResources { enabled = false }
    test { useJUnitPlatform() }
    jar { dependsOn("test") }
}