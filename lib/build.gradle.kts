plugins {
    alias(libs.plugins.java.library)

    alias(libs.plugins.kotlin.jvm)

    alias(libs.plugins.maven.publish)
}

group = "foundation.esoteric"
version = "1.0.0"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly(libs.paper)

    compileOnly(libs.adventureapi)
    implementation(libs.commandapi)

    implementation(libs.esoteric.utility)

    implementation(libs.commons.io)

    testRuntimeOnly(libs.junit.platform)
    testImplementation(libs.junit.jupiter.engine)

    testImplementation(libs.junit.kotlin)

    testImplementation(libs.mockbukkit)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Jar>("jar") {
    archiveFileName.set("${rootProject.name}-${project.version}.jar")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = rootProject.name
            version = version.toString()

            artifact(sourcesJar.get())
        }
    }
}
