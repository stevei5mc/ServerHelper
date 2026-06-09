plugins {
    alias(libs.plugins.shadow)
}

dependencies {
    api(project(":ServerHelper-Common"))
    compileOnly(libs.nukkit)
    compileOnly("com.github.stevei5mc:AutoRestart:1.0.1")
    compileOnly("cn.lanink:MemoriesOfTime-GameCore:1.6.13")
    compileOnly("com.smallaswater.ServerInfo:ServerInfo:1.1.0-SNAPSHOT")
    testImplementation(libs.nukkit)
}

tasks{
    processResources {
        from("src/main/resources") { expand(
            "version" to project.version
        )}
    }
    shadowJar {
        dependsOn("test")
        archiveClassifier.set("")
    }
    processTestResources { enabled = false }
    test { useJUnitPlatform() }
}