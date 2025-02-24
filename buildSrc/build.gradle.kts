//import org.gradle.internal.impldep.junit.runner.Version.id

plugins {
  `kotlin-dsl`}
//  id "com.github.johnrengelman.shadow" version "8.1.1"

//}
//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(21))
//    }
//}
//
//// Set the Kotlin JVM target for all Kotlin compile tasks
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "21"
//}
repositories {
  mavenCentral()
 gradlePluginPortal()
    jcenter()
}

