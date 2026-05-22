tasks {
    processTestResources { enabled = false }
    test { useJUnitPlatform() }
    jar { dependsOn("test") }
}