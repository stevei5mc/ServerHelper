import org.gradle.external.javadoc.StandardJavadocDocletOptions

plugins {
    id("java")
}

subprojects {
    group = "cn.stevei5mc.serverhelper"
    version = "1.0.0-beta1-SNAPSHOT"

    apply(plugin = "java")
    apply(plugin = "java-library")

    java {
        withJavadocJar()
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        mavenLocal()
        maven("https://repo.opencollab.dev/main")
        maven("https://repo.lanink.cn/repository/maven-public")
        maven("https://jitpack.io")
        mavenCentral()
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:24.0.1")
        compileOnly("org.projectlombok:lombok:1.18.30")
        annotationProcessor("org.projectlombok:lombok:1.18.30")
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
        withType<Javadoc> {
            options.encoding = "UTF-8"
            (options as StandardJavadocDocletOptions).apply {
                addStringOption("Xdoclint:all,-missing", "-quiet")
            }
        }
        withType<Jar> {
            destinationDirectory.set(file("$projectDir/target"))
        }
        named<Delete>("clean") {
            delete("target")
        }
        named<Copy>("processResources") {
            filteringCharset = "UTF-8"
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }
}