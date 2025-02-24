//plugins {
//  kotlin("jvm") version Libs.kotlinVersion // Add JVM plugin for Kotlin
//  kotlin(Plugins.kotlinxSerialization) version Libs.kotlinVersion
//  id(Plugins.flywayPlugin) version (Plugins.flywayVersion)
//  id(Plugins.kotlinKapt)
//  application
//}
//dependencies {
//  implementation(project(Modules.TASKMASTER.domain))
//  implementation(project(Modules.TASKMASTER.Data.psql))
//  implementation("redis.clients:jedis:5.0.0")
////  implementation("org.jetbrains.exposed:exposed-core:0.45.0")
////  implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
////  implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
////  implementation("org.jetbrains.exposed:exposed-java-time:0.45.0") // Add this for `timestampWithTimeZone`
//  implementation("org.postgresql:postgresql:42.6.0")
//  implementation("org.jetbrains.exposed:exposed-core:0.43.0")
//  implementation("org.jetbrains.exposed:exposed-dao:0.43.0")
//  implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0")
////  implementation("org.postgresql:postgresql:42.6.0")
//  implementation("redis.clients:jedis:5.1.0")
//  kapt("com.google.dagger:dagger-compiler:2.50")
//
//}
//
//val env = project.properties["env"] as String? ?: "dev"
//val resourcesFolder = "src/main/resources"
//val configFolder = "src/main/config"
//
//tasks.register<Copy>("copyResources") {
//  from("$configFolder/$env")
//  into(resourcesFolder)
//}
//
//tasks.findByName("processResources")
//  ?.dependsOn("copyResources")
//
//tasks.register<Delete>("cleanResources") {
//  File("$projectDir/$resourcesFolder").listFiles()
//    ?.forEach { it.delete() }
//}
//
//project.tasks.findByName("flywayMigrate")
//
//tasks
//  .findByName("clean")
//  ?.dependsOn("cleanResources")
//






plugins {
  kotlin(Plugins.kotlinxSerialization) version Libs.kotlinVersion
  id(Plugins.flywayPlugin) version (Plugins.flywayVersion)
  id(Plugins.kotlinKapt)
}

dependencies {
  implementation(project(Modules.TASKMASTER.domain))
  implementation(project(Modules.TASKMASTER.Data.psql))

  implementation(Libs.Log4j.core)
  implementation(Libs.Log4j.slf4jImpl)
  implementation("redis.clients:jedis:5.0.0")
//  implementation("org.jetbrains.exposed:exposed-core:0.45.0")
//  implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
//  implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
//  implementation("org.jetbrains.exposed:exposed-java-time:0.45.0") // Add this for `timestampWithTimeZone`
  implementation("org.postgresql:postgresql:42.6.0")
  implementation("org.jetbrains.exposed:exposed-core:0.43.0")
  implementation("org.jetbrains.exposed:exposed-dao:0.43.0")
  implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0")
//  implementation("org.postgresql:postgresql:42.6.0")
  implementation("redis.clients:jedis:5.1.0")
  kapt("com.google.dagger:dagger-compiler:2.50")
  implementation(Libs.Micrometer.cloudwatch)

  implementation(Libs.Ktor.clientCio)
  implementation(Libs.Ktor.clientJson)
  implementation(Libs.Ktor.clientJackson)
  implementation(Libs.Ktor.contentNegotiation)
  implementation(Libs.Ktor.serverContentNegotiation)
  implementation(Libs.Ktor.statusPages)
  implementation(Libs.Ktor.doubleReceive)
  implementation(Libs.Ktor.forwardedHeaders)
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

  implementation(Libs.KotlinUtils.exposed)
  implementation(Libs.hikariCP)

  implementation(Libs.sentry)

  implementation(Libs.KotlinUtils.openTracing)
  implementation(Libs.KotlinUtils.awsSqs)
  implementation(Libs.KotlinUtils.awsS3)
  implementation(Libs.KotlinUtils.serdeJackson)
  implementation(Libs.KotlinUtils.ktorWebClient)

  implementation(Libs.Dagger.dagger)
  kapt(Libs.Dagger.compiler)

  implementation(Libs.elasticEcs)
  implementation(Libs.shadowTransformer)
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
