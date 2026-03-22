plugins {
    alias(libs.plugins.shadow)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    maven("https://repo.waterdog.dev/main")
}

dependencies {
    api(project(":ServerHelper-Common"))
    compileOnly("dev.waterdog.waterdogpe:waterdog:2.0.4-SNAPSHOT")
}

tasks{
    processResources {
        from("src/main/resources") { expand(
            "version" to project.version
        )}
        from("${rootDir}/resources")
    }
    shadowJar { archiveClassifier.set("") }
    testClasses  { enabled = false }
    test { enabled = false }
}