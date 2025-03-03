plugins {
 id(Plugins.kotlinKapt)
//  kotlin("jvm") version "1.9.22"  // Ensure the Kotlin version matches
//  kotlin("plugin.serialization") version "1.9.22"
  kotlin("jvm") version Libs.kotlinVersion // Add JVM plugin for Kotlin
  kotlin(Plugins.kotlinxSerialization) version Libs.kotlinVersion

}

dependencies {
  implementation(project(Modules.TASKMASTER.client))
    implementation("redis.clients:jedis:5.1.0")

        implementation(Libs.kotlinxSerializationJson)
        implementation(Libs.kotlinxDatetime)


        implementation("org.apache.logging.log4j:log4j-core:2.22.1")
        implementation("org.apache.logging.log4j:log4j-api:2.22.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    // t(Modules.TASKMASTER.domain))
    implementation(project(Modules.TASKMASTER.domain))
    //implementation(project(Modules.TASKMASTER.Api.service))
 // compileOnly(project(Modules.TASKMASTER.Api.models))
  implementation(Libs.KotlinUtils.openTracing)
  implementation(Libs.KotlinUtils.exposed)
  implementation(Libs.Micrometer.core)
  implementation(Libs.Dagger.dagger)
  kapt(Libs.Dagger.compiler)
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation(Libs.Redis.jedis)




}




