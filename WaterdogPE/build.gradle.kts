java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    maven("https://repo.waterdog.dev/main")
}

dependencies {
    compileOnly("dev.waterdog.waterdogpe:waterdog:2.0.3")
}


tasks.processResources {
    from("${rootDir}/resources")
    into("build/resources/main")
}