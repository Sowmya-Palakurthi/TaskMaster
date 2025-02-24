
//dependencies {
//  implementation(Libs.KotlinUtils.openTracing)
//  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
//
//}
////implementation(project(Modules.TASKMASTER.))}




plugins {
  id(Plugins.kotlinKapt)
  kotlin("jvm") version "1.9.22"  // Ensure the Kotlin version matches
  kotlin("plugin.serialization") version "1.9.22"

}

dependencies {
//implementation(project(":api-models"))
  implementation(Libs.KotlinUtils.openTracing)

  //implementation(project(Modules.TASKMASTER.domain))
  implementation(Libs.KotlinUtils.openTracing)
  implementation(Libs.KotlinUtils.exposed)
  implementation(Libs.Micrometer.core)
  implementation(Libs.Dagger.dagger)
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

//
//plugins {
//    id(Plugins.kotlinKapt)
//}
//dependencies {
//    implementation(project(Modules.TASKMASTER.domain))
//    implementation(Libs.KotlinUtils.openTracing)
//    implementation(Libs.KotlinUtils.exposed)
//
//
//    implementation(Libs.Micrometer.core)
//    implementation(Libs.Dagger.dagger)
//    kapt(Libs.Dagger.compiler)
//}
