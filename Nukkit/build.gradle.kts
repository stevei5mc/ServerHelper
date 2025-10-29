plugins {
    alias(libs.plugins.shadow)
}

dependencies {
    api(project(":ServerHelper-Common"))
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    compileOnly("com.github.stevei5mc:AutoRestart:1.0.1")
    compileOnly("cn.lanink:MemoriesOfTime-GameCore:1.6.13")
}

tasks{
    processResources {
        from("src/main/resources") { expand(
            "version" to project.version
        )}
        from("${rootDir}/resources")
    }
    shadowJar {
        archiveClassifier.set("")
    }
}