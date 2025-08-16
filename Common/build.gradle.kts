plugins {
    alias(libs.plugins.shadow)
}

tasks.shadowJar {
    archiveClassifier.set("")
}