plugins {
  id(Plugins.kotlinKapt)
  kotlin("jvm") version "1.9.22"  // Ensure the Kotlin version matches
  kotlin("plugin.serialization") version "1.9.22"

}

dependencies {
  implementation(project(Modules.TASKMASTER.domain))
  implementation(Libs.KotlinUtils.openTracing)
  implementation(Libs.KotlinUtils.exposed)
  implementation(Libs.Micrometer.core)
  implementation(Libs.Dagger.dagger)
  kapt(Libs.Dagger.compiler)

  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}
