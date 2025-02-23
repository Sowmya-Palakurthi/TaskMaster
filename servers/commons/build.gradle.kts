plugins {
  kotlin("jvm") version Libs.kotlinVersion // Add JVM plugin for Kotlin
  kotlin(Plugins.kotlinxSerialization) version Libs.kotlinVersion
  id(Plugins.flywayPlugin) version (Plugins.flywayVersion)
  id(Plugins.kotlinKapt)
  application
}
dependencies {
  implementation(project(Modules.TASKMASTER.domain))
  implementation(project(Modules.TASKMASTER.Data.psql))
  implementation("redis.clients:jedis:5.0.0")
  implementation("org.jetbrains.exposed:exposed-core:0.45.0")
  implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
  implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
  implementation("org.jetbrains.exposed:exposed-java-time:0.45.0") // Add this for `timestampWithTimeZone`
  implementation("org.postgresql:postgresql:42.6.0") // PostgreSQL Driver

}

val env = project.properties["env"] as String? ?: "dev"
val resourcesFolder = "src/main/resources"
val configFolder = "src/main/config"

tasks.register<Copy>("copyResources") {
  from("$configFolder/$env")
  into(resourcesFolder)
}

tasks.findByName("processResources")
  ?.dependsOn("copyResources")

tasks.register<Delete>("cleanResources") {
  File("$projectDir/$resourcesFolder").listFiles()
    ?.forEach { it.delete() }
}

project.tasks.findByName("flywayMigrate")

tasks
  .findByName("clean")
  ?.dependsOn("cleanResources")
