plugins {
    alias(libs.plugins.kotlin.jvm)

    `java-library`

    id("maven-publish")
}

group = "foundation.esoteric"
version = "0.5.0"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("com.github.seeseemelk:MockBukkit-v1.21:3.107.0")

    api(libs.commons.math3)

    implementation(libs.guava)

    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("net.kyori:adventure-api:4.17.0")

    implementation("dev.jorel", "commandapi-bukkit-shade-mojang-mapped", "9.6.0")

    implementation("commons-io:commons-io:2.17.0")

    implementation("com.github.EsotericFoundation:utility.kt:0.4.0")
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
