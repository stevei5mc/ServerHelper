dependencies {
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

tasks {
    processResources {
        enabled = false
    }
    test {
        useJUnitPlatform()
    }
}