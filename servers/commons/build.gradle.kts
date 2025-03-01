//plugins {
//  kotlin("jvm") version Libs.kotlinVersion // Add JVM plugin for Kotlin
//  kotlin(Plugins.kotlinxSerialization) version Libs.kotlinVersion
//  id(Plugins.flywayPlugin) version (Plugins.flywayVersion)
//  id(Plugins.kotlinKapt)
//  application

  plugins {
    kotlin(Plugins.kotlinxSerialization) version Libs.kotlinVersion
    id(Plugins.flywayPlugin) version (Plugins.flywayVersion)
    id(Plugins.kotlinKapt)
    application
  }

  dependencies {
    implementation(project(Modules.TASKMASTER.domain))
    implementation(project(Modules.TASKMASTER.Data.psql))
//    implementation(project(Modules.TASKMASTER.))
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation(Libs.Log4j.core)
    implementation(Libs.Log4j.slf4jImpl)
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation(Libs.Micrometer.cloudwatch)

    implementation(Libs.Ktor.clientCio)
    implementation(Libs.Ktor.clientJson)
    implementation(Libs.Ktor.clientJackson)
    implementation(Libs.Ktor.contentNegotiation)
    implementation(Libs.Ktor.serverContentNegotiation)
    implementation(Libs.Ktor.statusPages)
    implementation(Libs.Ktor.doubleReceive)
    implementation(Libs.Ktor.forwardedHeaders)

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
    implementation("redis.clients:jedis:5.1.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation(project(Modules.TASKMASTER.client))


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
























//dependencies {
//  implementation(project(Modules.TASKMASTER.domain))
//  implementation(project(Modules.TASKMASTER.Data.psql))
//  implementation("redis.clients:jedis:5.0.0")
//  implementation("org.jetbrains.exposed:exposed-core:0.45.0")
//  implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
//  implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
//  implementation("org.jetbrains.exposed:exposed-java-time:0.45.0") // Add this for `timestampWithTimeZone`
//  implementation("org.postgresql:postgresql:42.6.0") // PostgreSQL Driver
//
//  dependencies {
//    implementation("org.jetbrains.exposed:exposed-core:0.45.0") // No new version provided, keeping the same
//    implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
//    implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
//    implementation("org.jetbrains.exposed:exposed-java-time:0.45.0") // Needed for `timestampWithTimeZone`
//    implementation("org.postgresql:postgresql:42.6.0") // No new version provided, keeping the same
//
//    // Plugins versions from the provided object
//    implementation("org.flywaydb:flyway-core:${Plugins.flywayVersion}") // 6.2.0
//    implementation("org.sonatype.gradle.plugins.scan:${Plugins.sonaTypePluginVersion}") // 2.6.0
//    implementation("org.gradle.toolchains.foojay-resolver-convention:${Plugins.toolChainPluginVersion}") // 0.7.0
//    implementation("com.github.brodziakm.maven-s3:${Plugins.mavenS3Version}") // 1.4.0
//    implementation("com.github.johnrengelman.shadow:${Plugins.fatJarPluginVersion}") // 8.1.1
//
//    // Detekt
//    implementation("io.gitlab.arturbosch.detekt:detekt-cli:${Plugins.Detekt.version}") // 1.23.4
//
//    // Jacoco
//    implementation("org.jacoco:org.jacoco.core:${Plugins.Jacoco.version}") // 0.8.12
//
//    // SonarQube
//    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Plugins.SonarQube.version}") // 4.4.1.3373
//
//
//    // Kotlinx Serialization
////    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
////
////    // Java Time support for Kotlinx Serialization (Optional, if you prefer a ready-made solution)
////    implementation("org.jetbrains.kotlinx:kotlinx-serialization-java-time:1.6.0")
//
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
