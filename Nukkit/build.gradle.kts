dependencies {
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    compileOnly("com.github.stevei5mc:AutoRestart:1.0.0")
    compileOnly("cn.lanink:MemoriesOfTime-GameCore:1.6.13")
}

tasks.processResources {
    filteringCharset = "UTF-8"
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    from("src/main/resources") { expand(
        "version" to project.version
    )}
    from("${rootDir}/resources")
    into("build/resources/main")
}