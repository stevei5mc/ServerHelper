dependencies {
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    compileOnly("com.github.stevei5mc:AutoRestart:1.0.0")
    compileOnly("cn.lanink:MemoriesOfTime-GameCore:1.6.13")
}

tasks.processResources {
    from("${rootDir}/resources")
    into("build/resources/main")
}