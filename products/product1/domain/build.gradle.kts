//dependencies {
//  implementation(Libs.KotlinUtils.openTracing)
//  implementation(project(Modules.TASKMASTER.Api.models))
//  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
//  //implementation(project(Modules.TASKMASTER.Data.psql))
//
//}



plugins {
  id(Plugins.kotlinKapt)
//  kotlin("jvm") version "1.9.22"  // Ensure the Kotlin version matches
//  kotlin("plugin.serialization") version "1.9.22"
  kotlin("jvm") version Libs.kotlinVersion // Add JVM plugin for Kotlin
  kotlin(Plugins.kotlinxSerialization) version Libs.kotlinVersion

}

dependencies {


    implementation(Libs.KotlinUtils.openTracing)
    //implementation(project(Modules.TASKMASTER.Api.models))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    //implementation(project(Modules.TASKMASTER.Data.psql))
  implementation(project(Modules.TASKMASTER.client))
  //compileOnly(project(Modules.TASKMASTER.domain))
  //implementation(project(Modules.TASKMASTER.domain))
  //implementation(project(Modules.TASKMASTER.Api.service))
  // compileOnly(project(Modules.TASKMASTER.Api.models))
  implementation(Libs.KotlinUtils.openTracing)
  implementation(Libs.KotlinUtils.exposed)
  implementation(Libs.Micrometer.core)
  implementation(Libs.Dagger.dagger)
  kapt(Libs.Dagger.compiler)
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
 // implementation(Libs.Redis.jedis)



}
