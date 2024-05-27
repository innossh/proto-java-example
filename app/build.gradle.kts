plugins {
    application
    id("com.google.protobuf") version "0.9.4"
}

repositories {
    mavenCentral()
}

val protobufVersion: String by extra("4.27.0")

dependencies {
    implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")

    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
}

application {
    mainClass = "innossh.proto.example.struct.StructExample"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
